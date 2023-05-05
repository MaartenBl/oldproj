using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Models;
using CIMS_Models.Environment;
using Microsoft.Extensions.Caching.Memory;
using Newtonsoft.Json;

namespace CIMS_Data.Repositories
{
    public class EnvironmentRepository : IEnvironmentRepository
    {
        private readonly HttpClient _client;
        private const string BaseUrl = "https://api.luchtmeetnet.nl/open_api";
        private List<EnvironmentStation> _stations = new List<EnvironmentStation>();
        private readonly IMemoryCache  _cache;

        public EnvironmentRepository(HttpClient client, IMemoryCache cache, string localWeatherStations = "RIVM-WeatherStations.json")
        {
            _cache = cache;
            _client = client;
            GetEnvironmentStations(localWeatherStations);
        }

        private void GetEnvironmentStations(string path)
        {
            if (File.Exists(path))
            {
                _stations = JsonConvert.DeserializeObject<List<EnvironmentStation>>(File.ReadAllText(path));
            }
            else
            {
                _stations = RetrieveStationsFromApi().Result;
                if (path != null)
                {
                    File.WriteAllText(path, JsonConvert.SerializeObject(_stations));
                }
            }
        }

        private async Task<List<EnvironmentStation>> RetrieveStationsFromApi()
        {
            var stationTasks = new List<Task<StationsList>>();
            var apiStations = new List<EnvironmentStation>();
            
            const int stationPages = 5;
            for (var i = 1; i < stationPages; i++)
            {
                stationTasks.Add(_client.GetJson<StationsList>($"{BaseUrl}/stations?order_by=&organisation_id=&page={i}"));
            }

            foreach (var task in stationTasks)
            {
                foreach (var (number, location) in task.Result.Data)
                {
                    var stationResp = await _client.GetJson<StationResponse>($"{BaseUrl}/stations/{number}");
                    var station = new EnvironmentStation()
                    {
                        Latitude = (float) stationResp.Data.Geometry.Coordinates[0],
                        Longitude = (float) stationResp.Data.Geometry.Coordinates[1],
                        Location = location,
                        DutchDescription = stationResp.Data.Description.Nl,
                        EnglishDescription = stationResp.Data.Description.En,
                        Number = number,
                        Organisation = stationResp.Data.Organisation,
                        Province = stationResp.Data.Province
                    };
                    
                    apiStations.Add(station);
                }
            }

            return apiStations;
        }

        private async Task<EnvironmentData> GetMeasurementFromApi(EnvironmentStation station)
        {
            var data = new EnvironmentData
            {
                Latitude = station.Latitude, 
                Longitude = station.Longitude,
                Location = station.Location
            };
            
            var measurementTask = _client.GetJson<StationMeasurement>(
                $"{BaseUrl}/stations/{station.Number}/measurements?page=1&order=&order_direction=&formula=");
          
            var environmentMeasurement = (await measurementTask).Data.GroupBy(m => m.Formula)
                .Select(grp => grp.First())
                .Select(m => new EnvironmentMeasurement
                {
                    Formula = m.Formula,
                    Value = m.Value,
                    Timestamp = m.Timestamp_Measured
                });
            data.Measurement = environmentMeasurement;
            return data;
        }

        public async Task<EnvironmentData> GetEnvironmentData(double longitude, double latitude)
        {
            //Calculate the difference in the coordinates for the given coordinates and every station, then take the one with the lowest difference.
            var closest = _stations.OrderBy(s => Math.Abs(s.Latitude - latitude) + Math.Abs( s.Longitude - longitude )).First();

            return await _cache.GetOrCreateAsync(closest, entry =>
            {
                entry.Size = 1;
                entry.AbsoluteExpirationRelativeToNow = TimeSpan.FromMinutes(10);
                return GetMeasurementFromApi(closest);
            });
        }
    }
    #region Json Classes

    internal class Station
    {
        public string Number { get; set; }
        public string Location { get; set; }
        public void Deconstruct(out string number, out string location)
        {
            number = Number;
            location = Location;
        }
    }

    internal class StationsList
    {
        public List<Station> Data { get; set; }
    }

    internal class Measurement
    {
        public double Value { get; set; }
        public string Formula { get; set; }
        public DateTime Timestamp_Measured { get; set; }
    }

    internal class StationMeasurement
    {
        public List<Measurement> Data { get; set; }
    }
    
    public class Geometry
    {
        public string Type { get; set; }
        public List<double> Coordinates { get; set; }
    }

    public class Description
    {
        public string Nl { get; set; }
        public string En { get; set; }
    }

    public class Data
    {
        public string Type { get; set; }
        public object Municipality { get; set; }
        public List<string> Components { get; set; }
        public Geometry Geometry { get; set; }
        public string Url { get; set; }
        public string Province { get; set; }
        public string Organisation { get; set; }
        public string Location { get; set; }
        public string YearStart { get; set; }
        public Description Description { get; set; }
    }

    public class StationResponse
    {
        public Data Data { get; set; }
    }
    #endregion
}