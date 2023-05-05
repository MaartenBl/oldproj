using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CIMS_Models;
using CIMS_Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace GPS4_CIMS_API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class P2KController : ControllerBase
    {
        private readonly ILogger<P2KController> _logger;
        private readonly IP2KService _p2KService;

        public P2KController(ILogger<P2KController> logger, IP2KService p2KService)
        {
            _p2KService = p2KService;
            _logger = logger;
        }

        [HttpGet]
        public async Task<IEnumerable<P2KItem>> Get()
        {
            IEnumerable<P2KItem> response = await _p2KService.GetAlertsForEvent();
            return response;
        }
    }
}