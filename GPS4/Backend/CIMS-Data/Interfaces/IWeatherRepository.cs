using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;
using CIMS_Models.Environment;

namespace CIMS_Data.Interfaces
{
    public interface IWeatherRepository
    {
        Task<WeatherData> GetWeatherData(double longitude, double latitude);
        Task<List<WeatherForecast>> GetForecast();
    }
}