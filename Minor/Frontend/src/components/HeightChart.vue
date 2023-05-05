<template>
  <div class="app">
    <apexcharts  height="200px" type="line" :options="chartOptions" :series="series"></apexcharts>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: 'HeightChart',
  components: {
    apexcharts: VueApexCharts,
  },
  data: function() {
      return {
        chartOptions: {
          chart: {
            id: 'Height',
          },
          xaxis: {
            categories: [10, 20, 30, 40, 50, 60, 70, 80],
            title: {
              text: 'Time'
            }
          },
          yaxis: {
            title: {
              text: 'Meters'
            },
          },
        },
        series: [{
          name: 'Height',
          data: [30, 40, 45, 50, 49, 60, 70, 81]
        }]
      }
    },
    methods: { 	
    },
    beforeMount(){
        const max = 4;
        const min = 0;
        const newData = this.series[0].data.map(() => {
          return Math.floor(Math.random() * (max - min + 1)) + min
        })
        this.series = [{
          name: 'Height',
          data: newData
        }]
      }
}
</script>

<style scoped>

</style>
