using System;
using System.Collections.Generic;
using System.Threading;
using CIMS_Models;
using CIMS_Models.LiveUpdate;
using CIMS_Services.Interfaces;

namespace CIMS_Services.Implementations
{
    public class LiveUpdateService : ILiveUpdateService
    {
        private List<IObserver<LiveUpdateEvent>> observers = new List<IObserver<LiveUpdateEvent>>();

        public LiveUpdateService(/*Fill in required repositories/services here */)
        {
        }

        public void UpdateEvent(LiveUpdateEvent updateEvent)
        {
            lock (observers)
            {
                foreach (IObserver<LiveUpdateEvent> observer in observers)
                {
                    observer.OnNext(updateEvent);
                }
            }
        }
        
        public IDisposable Subscribe(IObserver<LiveUpdateEvent> observer)
        {
            lock (observers)
            {
                this.observers.Add(observer);
                return new Unsubscriber(observers, observer);
            }
        }
        
        private class Unsubscriber : IDisposable
        {
            private readonly List<IObserver<LiveUpdateEvent>>_observers;
            private readonly IObserver<LiveUpdateEvent> _observer;

            public Unsubscriber(List<IObserver<LiveUpdateEvent>> observers, IObserver<LiveUpdateEvent> observer)
            {
                this._observers = observers;
                this._observer = observer;
            }

            public void Dispose()
            {
                lock (_observers)
                {
                    if (_observer != null && _observers.Contains(_observer))
                        _observers.Remove(_observer);
                }
               
            }
        }
    }
    
}