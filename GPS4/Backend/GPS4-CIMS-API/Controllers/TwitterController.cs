using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;
using CIMS_Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace GPS4_CIMS_API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class TwitterController : ControllerBase
    {
        private readonly ILogger<TwitterController> _logger;
        private readonly ITwitterService _twitterService;

        public TwitterController(ILogger<TwitterController> logger, ITwitterService twitterService)
        {
            _twitterService = twitterService;
            _logger = logger;
        }

        [HttpGet("search/{query}")]
        public async Task<TweetObjectsList> Search(string query)
        {
            return await _twitterService.GetSearchResults(query, "100");
        }
        
        [HttpGet("geo/place/{query}")]
        public async Task<TwitterGeoPlace> Place(string query)
        {
            return await _twitterService.GetTwitterGeoPlace(query);
        }
    }
}