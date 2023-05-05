<template>
   <div id="unity-container" class="unity-desktop">
      <canvas id="unity-canvas" width=2000 height=1000 style="border:1px solid #000000;"></canvas>
      <div id="unity-loading-bar">
        <div id="unity-logo"></div>
        <div id="unity-progress-bar-empty">
          <div id="unity-progress-bar-full"></div>
        </div>
      </div>
      <div id="unity-warning"> </div>
      <div id="unity-footer">
        <div id="unity-webgl-logo"></div>
        <div id="unity-fullscreen-button"></div>
      </div>
      <button style="border:none;background-color:#DDDDDD;color:white" v-on:click="fullscreen">fullscreen</button>
    </div>
</template>

<script>

import createUnityInstance from "../assets/WebGL_Build/Build/WebGL_Build.loader";
import testData from "../assets/2021-12-6T15_28_4.json";
export default {
  name: 'WebGL',
  data : {
    unityInstance : {}
  },
  methods : { 
    quit () { this.unityInstance.Quit(() => console.log("Exited WebGL"));
    this.unityInstance = null; },
    fullscreen () { this.unityInstance.SetFullscreen(1); }
    },
  mounted () {
    console.log("Mounting WebGL");
    var container = document.getElementById("unity-container");
      var canvas = document.getElementById("unity-canvas");
      var loadingBar = document.getElementById("unity-loading-bar");
      var progressBarFull = document.getElementById("unity-progress-bar-full");
      var fullscreenButton = document.getElementById("unity-fullscreen-button");
      var warningBanner = document.getElementById("unity-warning");

      // Shows a temporary message banner/ribbon for a few seconds, or
      // a permanent error message on top of the canvas if type=='error'.
      // If type=='warning', a yellow highlight color is used.
      // Modify or remove this function to customize the visually presented
      // way that non-critical warnings and error messages are presented to the
      // user.
      function unityShowBanner(msg, type) {
        function updateBannerVisibility() {
          warningBanner.style.display = warningBanner.children.length ? 'block' : 'none';
        }
        var div = document.createElement('div');
        div.innerHTML = msg;
        warningBanner.appendChild(div);
        if (type == 'error') div.style = 'background: red; padding: 10px;';
        else {
          if (type == 'warning') div.style = 'background: yellow; padding: 10px;';
          setTimeout(function() {
            warningBanner.removeChild(div);
            updateBannerVisibility();
          }, 5000);
        }
        updateBannerVisibility();
      }

      var buildUrl = "http://www.alexkersjes.nl/Build";
      var loaderUrl = buildUrl + "/WebGL_Build.loader.js";
      var config = {
        dataUrl: buildUrl + "/WebGL_Build.data.gz",
        frameworkUrl: buildUrl + "/WebGL_Build.framework.js.gz",
        codeUrl: buildUrl + "/WebGL_Build.wasm.gz",
        streamingAssetsUrl: "StreamingAssets",
        companyName: "DefaultCompany",
        productName: "WebGL-Render",
        productVersion: "0.1",
        showBanner: unityShowBanner,
      };

      // By default Unity keeps WebGL canvas render target size matched with
      // the DOM size of the canvas element (scaled by window.devicePixelRatio)
      // Set this to false if you want to decouple this synchronization from
      // happening inside the engine, and you would instead like to size up
      // the canvas DOM size and WebGL render target sizes yourself.
      config.matchWebGLToCanvasSize = true;

      if (/iPhone|iPad|iPod|Android/i.test(navigator.userAgent)) {
        // Mobile device style: fill the whole browser client area with the game canvas:

        var meta = document.createElement('meta');
        meta.name = 'viewport';
        meta.content = 'width=device-width, height=device-height, initial-scale=1.0, user-scalable=no, shrink-to-fit=yes';
        document.getElementsByTagName('head')[0].appendChild(meta);
        container.className = "unity-mobile";

        // To lower canvas resolution on mobile devices to gain some
        // performance, uncomment the following line:
        // config.devicePixelRatio = 1;

        canvas.style.width = window.innerWidth + 'px';
        canvas.style.height = window.innerHeight + 'px';

        unityShowBanner('WebGL builds are not supported on mobile devices.');
      } else {
        // Desktop style: Render the game canvas in a window that can be maximized to fullscreen:

        canvas.style.width ='100%';
        canvas.style.height='500px';
        // ...then set the internal size to match
        canvas.width  = canvas.offsetWidth;
        canvas.height = canvas.offsetHeight;
      }

      loadingBar.style.display = "block";

      createUnityInstance(canvas, config, (progress) => {
          progressBarFull.style.width = 100 * progress + "%";
        }).then((unityInstance) => {
          this.unityInstance = unityInstance;
          loadingBar.style.display = "none";
          /*fullscreenButton.onclick = () => {
            unityInstance.SetFullscreen(1);
          };*/
          unityInstance.SendMessage('JsonReader', 'InsertData', JSON.stringify(testData)); // PUT DATA IN HERE
        }).catch((message) => {
          alert(message);
        });
  },
  beforeDestroy () {
    console.log("Unmount hook");
    this.unityInstance.Quit(() => console.log("Exited WebGL"));
    this.unityInstance = null;
  }
}
</script>


<!--<template>
  <unity src="src\assets\WebGL_Build\Build\WebGL_Build.framework.js.gz" width="1000" height="600" unityLoader="src\assets\WebGL_Build\Build\WebGL_Build.loader.js"></unity>  
</template>

<script>
  import Unity from 'vue-unity-webgl'
  

export default {
  name: 'UnityWebGL',
  components: {
    Unity
  },
}
</script>
