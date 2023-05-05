using System;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using CIMS_Models.Environment;
using CIMS_Services.Implementations;
using CIMS_Services.Interfaces;
using Moq;
using NUnit.Framework;

namespace CIMS_Tests
{
    public class EnvironmentTests
    {
        private Mock<IEnvironmentRepository> _mockEnvironmentRepo;
        private Mock<IWeatherRepository> _mockWeatherRepo;
        private IEnvironmentService _environmentService;
        private WeatherData _fakeWeatherData;
        private const string CodeOrangeMessage = "Code Orange, Extreme weather";
        private const string CodeYellowMessage = "Code yellow: Dangerous weather";

        [SetUp]
        public void Setup()
        {
            _fakeWeatherData = new WeatherData
            {
                Latitude = 5,
                Longitude = 5,
                AirPressure = 5,
                FeelTemperature = 5,
                GroundTemperature = 5,
                Humidity = 5,
                Precipitation = 5,
                RainFallLast24Hour = 5,
                RainFallLastHour = 5,
                SunPower = 5,
                Timestamp = DateTime.Now,
                WindDirection = "N",
                WindGusts = 5,
                WindSpeed = 5,
                WindDirectionDegrees = 5,
                WindSpeedBft = 5,
                Visibility = 3000,
                Temperature = 5,
                RadarUrl = null
            };
            
            _mockEnvironmentRepo = new Mock<IEnvironmentRepository>();
            _mockWeatherRepo = new Mock<IWeatherRepository>();
            _mockWeatherRepo.Setup(repo => repo.GetWeatherData(5, 5)).ReturnsAsync(_fakeWeatherData);
            _environmentService = new EnvironmentService(_mockEnvironmentRepo.Object, _mockWeatherRepo.Object);
            
        }

        [Test]
        public async Task TestBaseConditions()
        {
            Assert.AreNotEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
            Assert.AreNotEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        #region CodeOrangeTests

        [Test]
        public async Task TestCodeOrangeWindGusts()
        {
            _fakeWeatherData.WindGusts = 101;
            Assert.AreEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeOrangeVisibility()
        {
            _fakeWeatherData.Visibility = 5;
            Assert.AreEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeOrangeTemperature()
        {
            _fakeWeatherData.FeelTemperature = -30;
            Assert.AreEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeOrangeRainFall()
        {
            _fakeWeatherData.RainFallLast24Hour = 100;
            Assert.AreEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeOrangeStorm()
        {
            _fakeWeatherData.WindGusts = 80;
            _fakeWeatherData.RainFallLastHour = 60;
            Assert.AreEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeOrangeRainStorm()
        {
            _fakeWeatherData.RainFallLast24Hour = 80;
            Assert.AreEqual(CodeOrangeMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        #endregion

        #region CodeYellowTests
        
        [Test]
        public async Task TestCodeYellowWindGusts()
        {
            _fakeWeatherData.WindGusts = 80;
            Assert.AreEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeYellowVisibility()
        {
            _fakeWeatherData.Visibility = 20;
            Assert.AreEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeYellowTemperature()
        {
            _fakeWeatherData.FeelTemperature = -17;
            Assert.AreEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeOYelloRainFall()
        {
            _fakeWeatherData.RainFallLast24Hour = 69;
            Assert.AreEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeYellowStorm()
        {
            _fakeWeatherData.WindGusts = 64;
            _fakeWeatherData.RainFallLastHour = 45;
            Assert.AreEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        [Test]
        public async Task TestCodeYellowRainStorm()
        {
            _fakeWeatherData.RainFallLast24Hour = 72;
            Assert.AreEqual(CodeYellowMessage, (await _environmentService.GetWeatherData(5f, 5f)).WeatherDescriptionEnglish);
        }
        
        #endregion
    }
}