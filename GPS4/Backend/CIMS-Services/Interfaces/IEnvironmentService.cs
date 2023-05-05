using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;
using CIMS_Models.Environment;

namespace CIMS_Services.Interfaces
{
    public interface IEnvironmentService
    {
        public Task<EnvironmentData> GetEnvironmentData(float longitude, float latitude);
        //TODO Below two methods may not be necessary in the final product
        Task<WeatherData> GetWeatherData(float longitude, float latitude);
        Task<List<WeatherForecast>> GetWeatherForecast();
    }
}