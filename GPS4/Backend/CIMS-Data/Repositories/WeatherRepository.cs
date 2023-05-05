using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using CIMS_Models.Environment;

namespace CIMS_Data.Repositories
{
    public class WeatherRepository : IWeatherRepository
    {
        private const string Url = "https://data.buienradar.nl/2.0/feed/json";
        private HttpClient _client;
        private WeatherMeasurement _mostRecentData = new WeatherMeasurement();
        private readonly Timer _timer;

        public WeatherRepository(HttpClient client)
        {
            _client = client;
            _timer = new Timer(obj =>
            {
                lock (_mostRecentData)
                {
                    var responseTask = client.GetJson<WeatherMeasurement>(Url);
                    _mostRecentData = responseTask.Result;
                }
            }, null, 10, 1000 * 60 * 10);
        }
        
        public Task<WeatherData> GetWeatherData(double longitude, double latitude)
        {
            lock(_mostRecentData)
            {
                return Task.Run(() => { var closest = _mostRecentData.Actual.Stationmeasurements.OrderBy(s => Math.Abs(s.Lat - latitude) + Math.Abs( s.Lon - longitude )).First();
                    return new WeatherData
                    {
                        Latitude = closest.Lat,
                        Longitude = closest.Lon,
                        AirPressure = closest.Airpressure,
                        FeelTemperature = closest.Feeltemperature,
                        GroundTemperature = closest.Groundtemperature,
                        Humidity = closest.Humidity,
                        Precipitation = closest.Precipitation,
                        RainFallLast24Hour = closest.RainFallLast24Hour,
                        RainFallLastHour = closest.RainFallLastHour,
                        SunPower = closest.Sunpower,
                        Timestamp = closest.Timestamp,
                        WindDirection = closest.Winddirection,
                        WindGusts = closest.Windgusts,
                        WindSpeed = closest.Windspeed,
                        WindDirectionDegrees = closest.Winddirectiondegrees,
                        WindSpeedBft = closest.WindspeedBft,
                        Visibility = closest.Visibility,
                        Temperature = closest.Temperature,
                        RadarUrl = _mostRecentData.Actual.Actualradarurl
                    }; });
            }
        }

        public Task<List<WeatherForecast>> GetForecast()
        {
            lock (_mostRecentData)
            {
                return Task.Run(() =>
                {
                    return _mostRecentData.Forecast.Fivedayforecast.Select(forecast => new WeatherForecast
                        {
                            Day = forecast.Day,
                            MaxTemperature = forecast.Maxtemperature,
                            MaxTemperatureMax = forecast.MaxtemperatureMax,
                            MaxTemperatureMin = forecast.MaxtemperatureMin,
                            MinTemperature = forecast.Mintemperature,
                            MinTemperatureMax = forecast.MintemperatureMax,
                            MinTemperatureMin = forecast.MintemperatureMin,
                            MmRainMax = forecast.MmRainMax,
                            MmRainMin = forecast.MmRainMin,
                            RainChance = forecast.RainChance,
                            SunChance = forecast.SunChance,
                            WindDirection = forecast.WindDirection,
                            Wind = forecast.Wind,
                            WeatherDescription = forecast.Weatherdescription
                        })
                        .ToList();
                });
            }
        }
    }
    
    #region Json classes

    class Stationmeasurement
    {
        public int Stationid { get; set; }
        public string Stationname { get; set; }
        public double Lat { get; set; }
        public double Lon { get; set; }
        public string Regio { get; set; }
        public DateTime Timestamp { get; set; }
        public string Weatherdescription { get; set; }
        public string Iconurl { get; set; }
        public string GraphUrl { get; set; }
        public string Winddirection { get; set; }
        public double Temperature { get; set; }
        public double Groundtemperature { get; set; }
        public double Feeltemperature { get; set; }
        public double Windgusts { get; set; }
        public double Windspeed { get; set; }
        public int WindspeedBft { get; set; }
        public double Humidity { get; set; }
        public double Precipitation { get; set; }
        public double Sunpower { get; set; }
        public double RainFallLast24Hour { get; set; }
        public double RainFallLastHour { get; set; }
        public int Winddirectiondegrees { get; set; }
        public double? Airpressure { get; set; }
        public double? Visibility { get; set; }
    }

    class Actual
    {
        public string Actualradarurl { get; set; }
        public DateTime Sunrise { get; set; }
        public DateTime Sunset { get; set; }
        public List<Stationmeasurement> Stationmeasurements { get; set; }
    }

    class Weatherreport
    {
        public DateTime Published { get; set; }
        public string Title { get; set; }
        public string Summary { get; set; }
        public string Text { get; set; }
        public string Author { get; set; }
        public string Authorbio { get; set; }
    }

    class Shortterm
    {
        public DateTime Startdate { get; set; }
        public DateTime Enddate { get; set; }
        public string Forecast { get; set; }
    }

    class Longterm
    {
        public DateTime Startdate { get; set; }
        public DateTime Enddate { get; set; }
        public string Forecast { get; set; }
    }

    class Fivedayforecast
    {
        public DateTime Day { get; set; }
        public string Mintemperature { get; set; }
        public string Maxtemperature { get; set; }
        public int MintemperatureMax { get; set; }
        public int MintemperatureMin { get; set; }
        public int MaxtemperatureMax { get; set; }
        public int MaxtemperatureMin { get; set; }
        public int RainChance { get; set; }
        public int SunChance { get; set; }
        public string WindDirection { get; set; }
        public int Wind { get; set; }
        public double MmRainMin { get; set; }
        public double MmRainMax { get; set; }
        public string Weatherdescription { get; set; }
        public string Iconurl { get; set; }
    }

    class Forecast
    {
        public Weatherreport Weatherreport { get; set; }
        public Shortterm Shortterm { get; set; }
        public Longterm Longterm { get; set; }
        public List<Fivedayforecast> Fivedayforecast { get; set; }
    }

    class WeatherMeasurement
    {
        public Actual Actual { get; set; }
        public Forecast Forecast { get; set; }
    }
    #endregion
}