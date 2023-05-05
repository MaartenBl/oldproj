using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;

namespace CIMS_Data.Interfaces
{
    public interface IP2KRepository
    {
        public IObservable<P2KItem> GetRecentItem();
        public Task<IEnumerable<P2KItem>> GetP2KItems();
    }
}