using System;
using System.Collections.Generic;

using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using CIMS_Services.Interfaces;

namespace CIMS_Services.Implementations
{
    public class TwitterService : ITwitterService
    {
        private readonly ITwitterRepository _twitterRepository;
        public TwitterService(ITwitterRepository twitterRepository)
        {
            _twitterRepository = twitterRepository;
        }


        public Task<TweetObjectsList> GetSearchResults(string query, string maxResults)
        {
            DateTime dateTimeNow = DateTime.Now.ToUniversalTime().AddMinutes(-1);
            DateTime dateTimeTwoWeeksAgo = dateTimeNow.AddDays(-14);
            return _twitterRepository.GetSearchResults(query, maxResults, dateTimeTwoWeeksAgo.ToString("yyyyMMddHHmm"), dateTimeNow.ToString("yyyyMMddHHmm"));
        }

        public Task<TwitterGeoPlace> GetTwitterGeoPlace(string place)
        {
            return _twitterRepository.GetTwitterGeoPlace(place);
        }
    }
}