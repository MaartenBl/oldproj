<template>
  <div class="app">
    <apexcharts  height="200px" type="bar" :options="chartOptions" :series="series"></apexcharts>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: 'Chart',
  components: {
    apexcharts: VueApexCharts,
  },
  data: function() {
      return {
        chartOptions: {
          chart: {
            id: 'Height-BarChart',
          },
          xaxis: {
            categories: [1, 2, 3, 4, 5, 6, 7, 8],
            title: {
              text: 'Run #',
            }
          },
          yaxis: {
            title: {
              text: 'Max Height (m)',
            }
          }
        },
        series: [{
          name: 'series-1',
          data: [3, 3.5, 1.4, 2.6, 2.6, 2.3, 4, 1.4]
        }]
      }
    },
    methods: { 	
    },
    beforeMount(){
        const max = 4.0;
        const min = 0.5;
        const newData = this.series[0].data.map(() => {
          return Math.floor(Math.random() * (max - min + 1)) + min
        })
        this.series = [{
          name: 'Max Height',
          data: newData
        }]
      }
}
</script>

<style scoped>

</style>
