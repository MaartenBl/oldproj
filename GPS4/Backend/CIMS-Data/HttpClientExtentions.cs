using System;
using System.Net.Http;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace CIMS_Data
{
    public static class HttpClientExtensions
    {
        public static async Task<T> GetJson<T>(this HttpClient httpClient, string url)
        {
            var httpTask = httpClient.GetAsync(url);
            var response = await httpTask;
            if (response.IsSuccessStatusCode)
            {
                return JsonConvert.DeserializeObject<T>(await response.Content.ReadAsStringAsync());
            }
            
            throw new HttpRequestException(response.Content.ReadAsStringAsync().Result);
        }
    }
}