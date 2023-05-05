using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Reactive.Subjects;
using System.Threading;
using System.Threading.Tasks;
using System.Xml;
using CIMS_Data.Config;
using CIMS_Data.Interfaces;
using CIMS_Models;
using Microsoft.Extensions.Options;
using Newtonsoft.Json;

namespace CIMS_Data.Repositories
{
    public class P2KRepository : IP2KRepository
    {
        private static List<P2KItem> _p2KItems;
        private readonly Subject<P2KItem> _recentItem = new Subject<P2KItem>();
        private readonly P2KConfig _config;
        private int MaxP2KItems;
        private int RefreshDelay;
        private Timer timer;

        public P2KRepository(IOptionsMonitor<P2KConfig> config)
        {
            _config = config.CurrentValue;
            _p2KItems = new List<P2KItem>();
            MaxP2KItems = _config.MaxCachedAlerts;
            RefreshDelay = _config.RefreshIntervalMs;
            
            GetIncidentData();
            timer = new Timer(obj =>
            {
                GetIncidentData();
            }, null, RefreshDelay, RefreshDelay);
        }
        
        public IObservable<P2KItem> GetRecentItem()
        {
            return _recentItem;
        }

        private async Task GetIncidentData()
        {
            var httpClientHandler = new HttpClientHandler();
            httpClientHandler.ServerCertificateCustomValidationCallback = (message, cert, chain, errors) => true; // DEBUGGING ONLY
            var httpClient = new HttpClient(httpClientHandler);
            XmlDocument p2kXml = new XmlDocument();
            //p2kXml.Load("https://feeds.livep2000.nl/");
            p2kXml.Load(await httpClient.GetStreamAsync("https://feeds.livep2000.nl/"));
            string json = JsonConvert.SerializeXmlNode(p2kXml);
            P2KRssModel serialized = JsonConvert.DeserializeObject<P2KRssModel>(json);

            IEnumerable<P2KItem> items = serialized.rss.channel.items;
            
            lock (_p2KItems)
            {
                if (_p2KItems.Count > 0)
                {
                    string lastGuid = _p2KItems.First().guid;

                    foreach (P2KItem item in items)
                    {
                        if (item.guid != lastGuid)
                        {
                            _p2KItems.Insert(0, item);
                            _recentItem.OnNext(item);
                        }

                        break;
                    }
                }
                else
                {
                    _p2KItems.AddRange(items);
                }

                if (_p2KItems.Count > MaxP2KItems)
                {
                    _p2KItems.RemoveRange(MaxP2KItems-1, _p2KItems.Count-MaxP2KItems);
                }
            }
        }

        public async Task<IEnumerable<P2KItem>> GetP2KItems()
        {
            return _p2KItems;
        }
    }
}