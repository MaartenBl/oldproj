using System;
using System.Threading.Tasks;
using CIMS_Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json.Linq;

namespace GPS4_CIMS_API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class EnvironmentController : ControllerBase
    {
        private readonly ILogger<EnvironmentController> _logger;
        private readonly IEnvironmentService _environmentService;

        public EnvironmentController(ILogger<EnvironmentController> logger, IEnvironmentService environmentService)
        {
            _logger = logger;
            _environmentService = environmentService;
        }

        [HttpGet("{longitude}/{latitude}")]
        public async Task<IActionResult> GetEnvironmentData(float longitude, float latitude)
        {
            try
            {
                return Ok(new
                {
                    Environment =  await _environmentService.GetEnvironmentData(longitude, latitude),
                    Weather = await _environmentService.GetWeatherData(longitude, latitude),
                    Forecast = await _environmentService.GetWeatherForecast()
                });
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, $"Failed to get environmental data from long:{longitude}, lat:{latitude}");
                return BadRequest();
            }
        }
    }
}