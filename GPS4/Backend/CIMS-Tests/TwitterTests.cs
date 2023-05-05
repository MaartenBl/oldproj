using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CIMS_Data.Interfaces;
using CIMS_Data.Repositories;
using CIMS_Models;
using CIMS_Services.Implementations;
using CIMS_Services.Interfaces;
using Moq;
using NUnit.Framework;

namespace CIMS_Tests
{
    public class TwitterTests
    {
        private Mock<ITwitterRepository> mockTwitterRepo;
        private ITwitterService twitterService;
        
        [SetUp]
        public void Setup()
        {
            mockTwitterRepo = new Mock<ITwitterRepository>();
            twitterService = new TwitterService(mockTwitterRepo.Object);

            TweetObjectsList mockTwitterData = new TweetObjectsList()
            {
                Results = new List<TweetObject>()
                {
                    new TweetObject()
                    {
                        Created_at = "2020-05-08",
                        Id = "5",
                        Text = "ramp"
                    }
                }
            };
            
            mockTwitterRepo.Setup((repo => repo.GetSearchResults(It.IsAny<string>(), "10", It.IsAny<string>(), It.IsAny<string>()))).ReturnsAsync(new TweetObjectsList()
            {
                Results = new List<TweetObject>()
            });
            mockTwitterRepo.Setup((repo => repo.GetSearchResults("ramp", "10", It.IsAny<string>(), It.IsAny<string>()))).ReturnsAsync(mockTwitterData);
        }

        [Test]
        public async Task TestGetByText()
        {
            TweetObjectsList results = await twitterService.GetSearchResults("ramp", "10");
            Assert.AreEqual("ramp",  results.Results[0].Text);
        }
        
        [Test]
        public async Task TestGetByTextWrongText()
        {
            TweetObjectsList results = await twitterService.GetSearchResults("geen ramp", "10");
            Assert.AreEqual(0, results.Results.Count);
        }
        
    }
}