using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using CIMS_Models.Environment;
using CIMS_Services.Interfaces;

namespace CIMS_Services.Implementations
{
    public class EnvironmentService : IEnvironmentService
    {
        private readonly IEnvironmentRepository _environmentRepo;
        private readonly IWeatherRepository _weatherRepo;
        
        public EnvironmentService(IEnvironmentRepository environmentRepo, IWeatherRepository weatherRepo)
        {
            _environmentRepo = environmentRepo;
            _weatherRepo = weatherRepo;
        }
        
        //TODO analyze data for dangerous effects
        public Task<EnvironmentData> GetEnvironmentData(float longitude, float latitude)
        {
            return _environmentRepo.GetEnvironmentData(longitude, latitude);
        }

        //TODO May not be necessary in the final product but is here for integration testing
        public async Task<WeatherData> GetWeatherData(float longitude, float latitude)
        {
            //https://nl.wikipedia.org/wiki/Weeralarm
            
            var weather = await _weatherRepo.GetWeatherData(longitude, latitude);
            //Code yellow
            if ((weather.RainFallLast24Hour > 50 && weather.RainFallLastHour < 75) ||
                (weather.WindGusts > 75 && weather.WindGusts < 100) ||
                (weather.WindGusts > 60 && weather.WindGusts <= 75 && weather.RainFallLastHour > 30 && weather.RainFallLastHour <= 50)||
                (weather.Visibility < 200 && weather.Visibility >= 10) ||
                weather.Temperature > 35 ||
                (weather.FeelTemperature < -15 && weather.FeelTemperature >= -20))
            {
                weather.WeatherDescriptionEnglish = "Code yellow: Dangerous weather";
                weather.WeatherDescriptionDutch = "Code geel: Gevaarlijk geweer";
            }
            
            //Code orange 
            if (weather.RainFallLast24Hour > 75 ||
                (weather.WindGusts > 75 && weather.RainFallLastHour > 50) ||
                weather.WindGusts > 100 ||
                weather.Visibility < 10 ||
                weather.FeelTemperature < -20)
            {
                weather.WeatherDescriptionEnglish = "Code Orange, Extreme weather";
                weather.WeatherDescriptionDutch = "Code Oranje: G E K O L O N I S E E R D";
            }
            //Code red is only given by the KNMI


            return weather;
        }
        
        //TODO Same for this one
        public Task<List<WeatherForecast>> GetWeatherForecast()
        {
            return _weatherRepo.GetForecast();
        }
    }
}