using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;

namespace CIMS_Services.Interfaces
{
    public interface ITwitterService
    {
        Task<TweetObjectsList> GetSearchResults(string query, string maxResults);

        Task<TwitterGeoPlace> GetTwitterGeoPlace(string place);
    }
}