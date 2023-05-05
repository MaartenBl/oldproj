using System.Collections.Generic;

namespace CIMS_Models.Environment
{
    public class EnvironmentData
    {
        public double Longitude { get; set; }
        public double Latitude { get; set; }
        public string Location { get; set; }
        public IEnumerable<EnvironmentMeasurement> Measurement { get; set; }
    }
}