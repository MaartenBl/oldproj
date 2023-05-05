namespace CIMS_Models
{
    using System;
    using Newtonsoft.Json;
    
    public class TwitterGeoPlace
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        [JsonProperty("url")]
        public Uri Url { get; set; }

        [JsonProperty("place_type")]
        public string PlaceType { get; set; }

        [JsonProperty("name")]
        public string Name { get; set; }

        [JsonProperty("full_name")]
        public string FullName { get; set; }

        [JsonProperty("country_code")]
        public string CountryCode { get; set; }

        [JsonProperty("country")]
        public string Country { get; set; }

        [JsonProperty("contained_within")]
        public ContainedWithin[] ContainedWithin { get; set; }

        [JsonProperty("geometry")]
        public object Geometry { get; set; }

        [JsonProperty("polylines")]
        public object[] Polylines { get; set; }

        [JsonProperty("centroid")]
        public double[] Centroid { get; set; }

        [JsonProperty("bounding_box")]
        public BoundingBox BoundingBox { get; set; }
    }
    
    public class BoundingBox
    {
        [JsonProperty("type")]
        public string Type { get; set; }

        [JsonProperty("coordinates")]
        public double[][][] Coordinates { get; set; }
    }

    public class ContainedWithin
    {
        [JsonProperty("id")]
        public string Id { get; set; }

        [JsonProperty("url")]
        public Uri Url { get; set; }

        [JsonProperty("place_type")]
        public string PlaceType { get; set; }

        [JsonProperty("name")]
        public string Name { get; set; }

        [JsonProperty("full_name")]
        public string FullName { get; set; }

        [JsonProperty("country_code")]
        public string CountryCode { get; set; }

        [JsonProperty("country")]
        public string Country { get; set; }

        [JsonProperty("centroid")]
        public double[] Centroid { get; set; }

        [JsonProperty("bounding_box")]
        public BoundingBox BoundingBox { get; set; }

        [JsonProperty("attributes")]
        public ContainedWithinAttributes Attributes { get; set; }
    }

    public partial class ContainedWithinAttributes
    {
    }
}