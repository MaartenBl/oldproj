using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;

namespace CIMS_Data.Interfaces
{
    public interface ITwitterRepository
    {
        Task<TweetObjectsList> GetSearchResults(string query, string maxResults, string fromDate, string toDate);
        Task<TwitterGeoPlace> GetTwitterGeoPlace(string place);
    }
}