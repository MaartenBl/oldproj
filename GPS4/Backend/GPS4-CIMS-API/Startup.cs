using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.WebSockets;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using CIMS_Data.Config;
using CIMS_Data.Interfaces;
using CIMS_Data.MockRepository;
using CIMS_Data.Repositories;
using CIMS_Services.Implementations;
using CIMS_Services.Interfaces;
using GPS4_CIMS_API.Controllers;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.WebSockets;
using Microsoft.Extensions.Caching.Memory;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace GPS4_CIMS_API
{
    public class Startup
    {
        private readonly string MyAllowSpecificOrigins = "_myAllowSpecificOrigins";
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }
        private LiveUpdateService liveUpdateService { get; set; }
        private WebSocketController webSocketController { get; set; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddConnections();
            services.AddHttpClient();
            services.AddWebSockets(options => {
                options.KeepAliveInterval = TimeSpan.FromSeconds(120);
                options.ReceiveBufferSize = 4 * 1024;
            });
            
            liveUpdateService = new LiveUpdateService();
            webSocketController = new WebSocketController(liveUpdateService);
            
            services.AddSingleton<IP2KService, P2KService>();
            services.AddSingleton<ILiveUpdateService>(liveUpdateService);
            services.AddSingleton<IP2KRepository, P2KRepository>();
            services.AddMemoryCache(options => options.SizeLimit = 150);
            services.AddSingleton<HttpClient>(s => new HttpClient());
            
            services.AddSingleton<IEnvironmentRepository, EnvironmentRepository>(serv => 
                new EnvironmentRepository(serv.GetService<HttpClient>(), new MemoryCache(new MemoryCacheOptions
            {
                SizeLimit = 100
            })));
            services.AddSingleton<IWeatherRepository, WeatherRepository>();
            services.AddSingleton<IEnvironmentService, EnvironmentService>();
            services.Configure<P2KConfig>(Configuration.GetSection("P2K"));
            services.Configure<TwitterConfig>(Configuration.GetSection("twitter"));
            
            services.AddSingleton<ITwitterService, TwitterService>();
             services.AddSingleton<ITwitterRepository, TwitterRepository>();
           // services.AddSingleton<ITwitterRepository, MockTwitterRepository>();
            services.AddControllers();

            services.AddCors(options =>
            {
                options.AddPolicy(MyAllowSpecificOrigins,
                    builder =>
                    {
                        builder.WithOrigins("*")
                            .AllowAnyHeader()
                            .AllowAnyMethod();
                    });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseCors(MyAllowSpecificOrigins);
            
            app.UseWebSockets();
            app.UseRouting();

            app.UseAuthorization();
            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers(); 
                //endpoints.MapConnectionHandler<WebSocketController>("/ws");
                
            });
            
            app.Use(async (context, next) =>
            {
                if (context.Request.Path == "/ws")
                {
                    if (context.WebSockets.IsWebSocketRequest)
                    {
                        WebSocket webSocket = await context.WebSockets.AcceptWebSocketAsync();
                        
                        Console.WriteLine("Connection incoming");

                        await webSocketController.OnConnectedAsync(webSocket);
                        Console.WriteLine("closing");
                    }
                    else
                    {
                        context.Response.StatusCode = 400;
                    }
                }
                else
                {
                    await next();
                }
            
            });

            app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
            app.ApplicationServices.GetService<IP2KService>();
        }
    }
}