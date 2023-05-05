using System;

namespace CIMS_Models.Environment
{
    public class WeatherData
    {
        public double Longitude { get; set; }
        public double Latitude { get; set; }
        public DateTime Timestamp { get; set; }
        public string WeatherDescriptionDutch { get; set; }
        public string WeatherDescriptionEnglish { get; set; }
        public string WindDirection { get; set; }
        public double Temperature { get; set; }
        public double GroundTemperature { get; set; }
        public double FeelTemperature { get; set; }
        public double WindGusts { get; set; }
        public double WindSpeed { get; set; }
        public int WindSpeedBft { get; set; }
        public double Humidity { get; set; }
        public double Precipitation { get; set; }
        public double SunPower { get; set; }
        public double RainFallLast24Hour { get; set; }
        public double RainFallLastHour { get; set; }
        public int WindDirectionDegrees { get; set; }
        public double? AirPressure { get; set; }
        public double? Visibility { get; set; }
        public string RadarUrl { get; set; }
    }
}