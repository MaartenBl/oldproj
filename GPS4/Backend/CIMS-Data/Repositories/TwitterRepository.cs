using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using CIMS_Data.Config;
using CIMS_Data.Interfaces;
using CIMS_Models;
using Microsoft.Extensions.Options;
using Newtonsoft.Json;

namespace CIMS_Data.Repositories
{
    public class TwitterRepository : ITwitterRepository
    {
        private readonly string _jwtToken;

        public TwitterRepository(IOptionsMonitor<TwitterConfig> twitterConfig)
        {
            TwitterConfig currentTwitterConfig = twitterConfig.CurrentValue;
            _jwtToken = currentTwitterConfig.JwtToken;
        }
        
        public async Task<TweetObjectsList> GetSearchResults(string query, string maxResults, string fromDate, string toDate)
        {
            
            var client = new HttpClient();
            var request = new HttpRequestMessage(HttpMethod.Post, "https://api.twitter.com/1.1/tweets/search/30day/Development.json");
            request.Content = new StringContent("{\n    \"query\": \"" + query +
                                                " lang:en\",\n    \"maxResults\": \"100\",\n    \"fromDate\": \""+ fromDate+"\",\n    \"toDate\": \""+toDate+"\"\n}");
            request.Content.Headers.ContentType = new MediaTypeHeaderValue("application/json");
            //request.Content.Headers.Add("Content-Type", "application/json");
            request.Headers.Add("cache-control", "no-cache");
            request.Headers.Add("Authorization", _jwtToken);
          
            
            return await client.SendAsync(request)
                .ContinueWith(resp => 
                JsonConvert.DeserializeObject<TweetObjectsList>(resp.Result.Content.ReadAsStringAsync().Result)
                );
            
            /*var client = new RestClient("https://api.twitter.com/1.1/tweets/search/30day/Development.json");
            var request = new RestRequest(Method.POST);
            request.AddHeader("cache-control", "no-cache");
            request.AddHeader("Authorization", _jwtToken);
            request.AddHeader("Content-Type", "application/json");
            
            request.AddParameter("undefined",
                "{\n    \"query\": \"" + query +
                " lang:en\",\n    \"maxResults\": \"100\",\n    \"fromDate\": \""+ fromDate+"\",\n    \"toDate\": \""+toDate+"\"\n}",
                ParameterType.RequestBody);
            IRestResponse response = client.Execute(request);
            return JsonConvert.DeserializeObject<TweetObjectsList>(response.Content);*/
        }

        public Task<TwitterGeoPlace> GetTwitterGeoPlace(string place)
        {
            //No access to the endpoint at https://developer.twitter.com/en/docs/geo/place-information/api-reference/get-geo-id-place_id
            throw new NotImplementedException();
        }


        /*private IRestResponse TwitterResponse(string restUrl)
        {
            RestClient client = new RestClient(restUrl);
            client.Timeout = -1;
            return client.Execute(TwitterRestRequest());
        }


        private RestRequest TwitterRestRequest()
        {
            RestRequest request = new RestRequest(Method.GET);
            request.AddHeader("Authorization",
                _jwtToken);
            request.AddHeader("Cookie",
                "personalization_id=\"v1_iecXsjfoeQwFsGqD3CvH+g==\"; guest_id=v1%3A158633433358025992");
            return request;
        }*/
    }
}