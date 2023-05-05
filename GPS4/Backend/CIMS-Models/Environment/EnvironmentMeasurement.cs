using System;

namespace CIMS_Models.Environment
{
    public class EnvironmentMeasurement
    {
        public double Value { get; set; }
        public string Formula { get; set; }
        public DateTime Timestamp { get; set; }
    }
}