using System;
using System.Collections.Generic;
using System.Linq;
using CIMS_Models.LiveUpdate;
using Newtonsoft.Json.Linq;

namespace CIMS_Models
{
    public class P2KRssModel
    {
         public RssContent rss { get; set; }
    }

    public class RssContent
    {
        public RssChannel channel { get; set; }
    }

    public class RssChannel
    {
        public JObject title { get; set; }

        public IEnumerable<P2KItem> items
        {
            get
            {
                List<P2KItem> returnItems = new List<P2KItem>();
                
                foreach (JObject jobj in item)
                {
                    P2KItem p2KItem = new P2KItem();
                    try
                    {
                        IEnumerable<string> units =  JObject.Parse(jobj["description"].ToString())["#cdata-section"].ToString().Split("<br/>").Where(x => !string.IsNullOrWhiteSpace(x)).ToList();
                        p2KItem.title = JObject.Parse(jobj["title"].ToString())["#cdata-section"].ToString();
                        p2KItem.link = jobj["link"].ToString();
                        p2KItem.date = DateTime.Parse(jobj["pubDate"].ToString());
                        p2KItem.unitCount = units.Count();
                        p2KItem.units = units;
                        p2KItem.guid = jobj["guid"].ToString();
                        p2KItem.lat = jobj["geo:lat"].ToString();
                        p2KItem.lon = jobj["geo:long"].ToString();
                    }
                    catch (Exception e)
                    {
                        
                    }
                    
                    returnItems.Add(p2KItem);
                }

                return returnItems;
            }
            set { items = value; }
        }

        public IEnumerable<JObject> item { get; set; }
    }

    public class P2KItem : Payload
    {
        public string title { get; set; }
        public string link { get; set; }
        public DateTime date { get; set; }
        public int unitCount { get; set; }
        public IEnumerable<string> units { get; set; }
        public string guid { get; set; }
        public string lat { get; set; }
        public string lon { get; set; }
    }
}