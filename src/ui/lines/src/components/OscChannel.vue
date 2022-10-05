<template>
<div>
<div id="channelInfo"></div>
<div style="height: 200px; width: 8000px">
<canvas :id="this.channelKey" style="height: 200px; width: 8000px"></canvas>
</div>
</div>
</template>
<script>
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

    export default {
    props: {
      channelKey:{},
    chartData: {
      type: Object,
      default: null
    },
    options: {}
  },
  mounted(){
const ctx = document.getElementById(this.channelKey).getContext('2d');
const myChart = new Chart(ctx, {
    type: 'line',
    data: this.chartData,
    options: {
        plugins:{
          title:{
            display:false
          },
          legend:{
            display:false
          }
        },
        scales: {
            
                x:{
                  ticks:{
                    display:false
                  }
                },
                y:{
                  ticks:{
                    display:false
                  }
                }
            
        },
        responsive: true,
        maintainAspectRatio: false,
        animation:false,
        animations:{colors:false, x:false},
        transitions:{active:{animation:{duration:0}}},

    }
    
});
myChart;
  }
 }

    
</script>

<style scoped>

</style>