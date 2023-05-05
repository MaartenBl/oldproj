using System.Collections.Generic;
using System.Threading.Tasks;
using CIMS_Models;
using CIMS_Models.Environment;

namespace CIMS_Data.Interfaces
{
    public interface IEnvironmentRepository
    {
        public Task<EnvironmentData> GetEnvironmentData(double longitude, double latitude);
    }
}