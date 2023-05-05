using System;
using System.Collections.Generic;
using Newtonsoft.Json.Linq;

namespace CIMS_Models
{

    public class Tweet
    {
        public List<TweetData> result;
    }
    public class TweetData
    {
        public string created_at { get; set; }
        public Int64 id { get; set; }
        public string id_str  { get; set; }
        public string text  { get; set; }
       // public Object user  { get; set; }
       // public Object entities  { get; set; }
    }
}