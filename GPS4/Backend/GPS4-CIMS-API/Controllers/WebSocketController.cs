using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.WebSockets;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using CIMS_Models.LiveUpdate;
using CIMS_Services.Interfaces;
using Microsoft.AspNetCore.Connections;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Http.Connections;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace GPS4_CIMS_API.Controllers
{
    //TODO make sure connection closes when client disconnects
    public class WebSocketController : IObserver<LiveUpdateEvent>
    {
        private ILogger<WebSocketController> logger;
        private Dictionary<WebSocket, TaskCompletionSource<object>> userWebSockets = new Dictionary<WebSocket, TaskCompletionSource<object>>();
        private ILiveUpdateService liveUpdateService;
        private IDisposable observerSubscription;
        
        public WebSocketController(/*ILogger<WebSocketController> logger,*/ ILiveUpdateService liveUpdateService)
        {
            // this.logger = logger;
            this.liveUpdateService = liveUpdateService;
            this.observerSubscription = liveUpdateService.Subscribe(this);
        }
        
        public async Task OnConnectedAsync(WebSocket webSocket)
        {
            TaskCompletionSource<object> task = new TaskCompletionSource<object>();
            
            userWebSockets.Add(webSocket, task);
            OnReceiveAsync(webSocket, task);
            //logger.LogInformation($"WebSocket request accepted");
            await task.Task;
            
            Console.WriteLine("daar ga je");
        }

        public async Task OnReceiveAsync(WebSocket webSocket, TaskCompletionSource<object> task)
        {
            var buffer = new byte[1024 * 4];
            var result = await webSocket.ReceiveAsync(buffer, CancellationToken.None);
            
            if (result.MessageType == WebSocketMessageType.Close)
            {
                await webSocket.CloseOutputAsync(WebSocketCloseStatus.NormalClosure, "Client initiated closure, pleurt maar lekker op dan", CancellationToken.None);
            }
            
            while (!result.CloseStatus.HasValue)
            {
                result = await webSocket.ReceiveAsync(new ArraySegment<byte>(buffer), CancellationToken.None);
                
                if (result.MessageType == WebSocketMessageType.Close)
                {
                    await webSocket.CloseOutputAsync(WebSocketCloseStatus.NormalClosure, "Client initiated closure, pleurt maar lekker op dan", CancellationToken.None);
                }
                
                
            }
            
            Console.WriteLine("closing");
            
            userWebSockets = userWebSockets.Where(kvp => kvp.Key.State != WebSocketState.Closed)
                .ToDictionary(pair => pair.Key,
                    pair => pair.Value);
        }

        // public override async Task OnConnectedAsync(ConnectionContext connection)
        // {
        //     WebSocket webSocket;
        //     if (connection.GetHttpContext().WebSockets.IsWebSocketRequest)
        //     {
        //         webSocket = await connection.GetHttpContext().WebSockets.AcceptWebSocketAsync();
        //         await webSocket.SendAsync(Encoding.ASCII.GetBytes("test"), WebSocketMessageType.Text, true,
        //             CancellationToken.None);
        //     }
        //     else
        //     {
        //         connection.GetHttpContext().Response.StatusCode = 400;
        //         return;
        //     }
        //     
        //     Console.WriteLine("Connection incoming");
        //     TaskCompletionSource<object> task = new TaskCompletionSource<object>();
        //
        //     Console.WriteLine("here");
        //     
        //     //userWebSockets.Add(webSocket, task);
        //     // logger.LogInformation($"WebSocket request accepted");
        //     await webSocket.SendAsync(Encoding.UTF8.GetBytes("Hello"), WebSocketMessageType.Text, true, CancellationToken.None);
        //     
        //     await task.Task;
        //     // logger.LogInformation("Connection closed");
        //     
        //     Console.WriteLine("closing");
        //     
        //     userWebSockets = userWebSockets.Where(kvp => kvp.Key.State != WebSocketState.Closed)
        //         .ToDictionary(pair => pair.Key,
        //             pair => pair.Value);
        // }

        public void OnCompleted()
        {
            observerSubscription.Dispose();
        }

        public void OnError(Exception error)
        {
            this.logger.LogError(error, "Error in Live Update");
        }

        public async void OnNext(LiveUpdateEvent update)
        {
            string jsonUpdate = JsonConvert.SerializeObject(update);
            foreach ((WebSocket socket, TaskCompletionSource<object> task) in userWebSockets)
            {
                try
                {
                    // this.logger.LogInformation("Sending live update", jsonUpdate);
                    await socket.SendAsync(Encoding.UTF8.GetBytes(jsonUpdate), WebSocketMessageType.Text, true,
                        CancellationToken.None);
                }
                catch (Exception ex)
                {
                    await socket.CloseAsync(WebSocketCloseStatus.Empty, "", CancellationToken.None);
                    task.SetException(ex);
                    logger.LogError(ex, "Error while sending update");
                }
            }
        }
    }
}