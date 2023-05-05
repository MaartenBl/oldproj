using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using CIMS_Models.LiveUpdate;
using CIMS_Services.Interfaces;

namespace CIMS_Services.Implementations
{
    public class P2KService: IP2KService
    {
        private readonly IP2KRepository _p2KRepository;
        private readonly ILiveUpdateService _liveUpdateService;

        public P2KService(IP2KRepository p2KRepository, ILiveUpdateService liveUpdateService)
        {
            _p2KRepository = p2KRepository;
            _liveUpdateService = liveUpdateService;
            
            _p2KRepository.GetRecentItem().Subscribe(
               item =>
               {
                   liveUpdateService.UpdateEvent(new LiveUpdateEvent
                   {
                       type = "P2K Alert",
                       payload = item
                   });
               } ,
               exception =>
               {
                   Console.WriteLine(exception);
               } 
               );
        }

        private async void GetIncidentData()
        {
            IEnumerable<P2KItem> items = await _p2KRepository.GetP2KItems();
            
        }
        
        public async Task<IEnumerable<P2KItem>> GetAlertsForEvent()
        {
            // get incident location and other relevant shit from db
            return await _p2KRepository.GetP2KItems();
        }
    }
}