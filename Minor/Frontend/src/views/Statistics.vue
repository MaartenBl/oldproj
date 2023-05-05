<template>
<div>
    <div>
        <SideBar/>
    </div>
    <div class="content">
        <div>
            <h1 class="title">Statistics</h1>
        </div>
        <hr>
        <div class="chart">
            <h2 class="title"> Max Acceleration (m.s^-2)</h2>
            <AccelerationChart/>
        </div>
        <hr>
        <div class="chart">
            <h2 class="title"> Max Velocity (m.s^-1)</h2>
            <VelocityChart/>
        </div>
        <hr>
        <div class="chart">
            <h2 class="title"> Max Height (m) </h2>
            <HeightChart/>
        </div>
        <hr>
    </div>
</div>
</template>

<script>
import SideBar from '../components/SideBar.vue'
import AccelerationChart from '../components/AccelerationChart.vue'
import VelocityChart from '../components/VelocityChart.vue'
import HeightChart from '../components/HeightChart.vue'

export default {
  name: 'Statistics',
  components: {
    SideBar,
    AccelerationChart,
    VelocityChart,
    HeightChart,
  }
}
</script>

<style scoped>

.title {
    text-align: center;
    font-size: 32px;
    font-weight: 100;
}
.content{
    position: relative;
    left: 20.1%;
    height: 100%;
    box-shadow: 0 6px 12px #999;
}
hr{
    border: 1px solid black;
}

</style>
