// using System.Collections.Generic;

using System.Collections.Generic;

namespace CIMS_Models
{
    public class TweetObject
    {
        public string Id { get; set; }
        public string Text { get; set; }
        public string Created_at { get; set; }
    }

    public class TweetObjectsList
    {
        public List<TweetObject> Results { get; set; }
    }
}