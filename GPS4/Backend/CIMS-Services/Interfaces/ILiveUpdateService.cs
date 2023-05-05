using System;
using CIMS_Models.LiveUpdate;

namespace CIMS_Services.Interfaces
{
    public interface ILiveUpdateService : IObservable<LiveUpdateEvent>
    {
        //Empty for future expansion
        public void UpdateEvent(LiveUpdateEvent updateEvent);
    }
}