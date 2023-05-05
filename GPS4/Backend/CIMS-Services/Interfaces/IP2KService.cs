using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;

namespace CIMS_Services.Interfaces
{
    public interface IP2KService
    {
        public Task<IEnumerable<P2KItem>> GetAlertsForEvent();
    }
}