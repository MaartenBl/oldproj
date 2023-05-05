using System;
using System.Collections.Generic;

namespace CIMS_Models
{
    public class SirenesApiResponse
    {
        public int Status { get; set; }
        public IEnumerable<SirenesApiResponseData> Data { get; set; }
    }

    public class SirenesApiResponseData
    {
        public string Capcodes { get; set; }
        public string Bericht { get; set; }
        public DateTime Timestamp { get; set; }
        public string Diensten { get; set; }
        public string Url { get; set; }
    }
}