<template>
  <div>
    
    <div class="row col-centered">
      <div class="wrapper col-sm-4 top">
        <h1>All Runs</h1>
        <hr class="col-sm-11 col-centered">
        <ul>
          <li>07/10/21_11:52</li>
          <li>07/11/21_12:54</li>
          <li>08/10/21_08:26</li>
          <li>11/11/21_16:12</li>
        </ul>
      </div>

      <div class="wrapper col-sm-7 top">
        <p>
          07/10/21_11:52
        </p>
        <WebGL/>
      </div>
    </div>

    <div class="row col-centered">
      <div class="wrapper col-sm-4 bot" >
        <router-link to="/Statistics">
          <center><p>Analytics - Detailed</p></center>
        </router-link>
        <Chart height="auto" />
      </div>

      <!--<div class="wrapper col-sm-7 bot">
        <img class="col-xl-12" src="" alt="Heatmap">
      </div>-->
    </div>

    <router-link to="/Home">
      <div class="return">
        <p>Main Dashboard</p>
      </div>
    </router-link>
  </div>
</template>

<script>
import Chart from '../components/Chart.vue'
import WebGL from '../components/WebGL.vue'
export default {
  name: 'App',
  components: {
    Chart,
    WebGL
  },
}
</script>



<style scoped>

*{
  text-decoration: none;
}

.return p{
  font-weight: 100;
  font-size: 32px;
  padding: 5px 25px;
  color: #000;
}

.wrapper{
  margin: auto;
  margin-top: 20px;
  background: #fff;
  border: 1px solid black;
  box-shadow: 0 3px 12px #999;
  overflow: hidden;
}

h1{
  padding: 10px 22px;
}

.top{
    height: 58vh;
}

.bot {
  height: 29vh;
}

ul{
    list-style: none;
    font-weight: 100;
    font-size: 32px;
}

li{
    padding: 5px 0;
}

</style>
