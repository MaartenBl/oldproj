using System;
using System.Collections.Generic;
using System.IO;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using Newtonsoft.Json;

namespace CIMS_Data.MockRepository
{
    public class MockTwitterRepository : ITwitterRepository
    {
        private readonly TweetObjectsList _tweetObjectsList;
        private readonly TwitterGeoPlace _twitterGeoPlace;

        public MockTwitterRepository()
        {
            _tweetObjectsList =
                JsonConvert.DeserializeObject<TweetObjectsList>(File.ReadAllText("MockTwitterSearchResult.json"));
            _twitterGeoPlace = JsonConvert.DeserializeObject<TwitterGeoPlace>(File.ReadAllText("MockTwitterPlaceResponse.json"));
        }

        public Task<TweetObjectsList> GetSearchResults(string query, string maxResults, string fromDate, string toDate)
        {
            return Task.Run(() => _tweetObjectsList);
        }

        public Task<TwitterGeoPlace> GetTwitterGeoPlace(string place)
        {
            return Task.Run(() => _twitterGeoPlace);
        }
    }
}