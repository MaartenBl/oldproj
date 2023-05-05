using System;

namespace CIMS_Models.Environment
{
    public class WeatherForecast
    {
        public DateTime Day { get; set; }
        public string MinTemperature { get; set; }
        public string MaxTemperature { get; set; }
        public int MinTemperatureMax { get; set; }
        public int MinTemperatureMin { get; set; }
        public int MaxTemperatureMax { get; set; }
        public int MaxTemperatureMin { get; set; }
        public int RainChance { get; set; }
        public int SunChance { get; set; }
        public string WindDirection { get; set; }
        public int Wind { get; set; }
        public double MmRainMin { get; set; }
        public double MmRainMax { get; set; }
        public string WeatherDescription { get; set; }
    }
}