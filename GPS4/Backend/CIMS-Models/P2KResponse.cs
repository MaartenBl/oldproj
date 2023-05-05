using System;

namespace CIMS_Models
{
    public class P2KResponse
    {
        public P2KResponse(){}
        public P2KResponse(int units, Array capcodes, DateTime date, string priority, P2KLocation location, string incident)
        {
            Units = units;
            Capcodes = capcodes;
            Date = date;
            Priority = priority;
            Location = location;
            IncidentMessage = incident;
        }

        public int Units { get; set; }
        public Array Capcodes { get; set; }
        public DateTime Date { get; set; }
        public string Priority { get; set; }
        public P2KLocation Location { get; set; }
        public string IncidentMessage { get; set; }
    }

    public class P2KLocation
    {
        public P2KLocation(){}
        public P2KLocation(string postalCode, string houseNumber, string street, string city)
        {
            PostalCode = postalCode;
            HouseNumber = houseNumber;
            Street = street;
            City = city;
        }

        public string PostalCode { get; set; }
        public string HouseNumber { get; set; }
        public string Street { get; set; }
        public string City { get; set; }
    }
    
}