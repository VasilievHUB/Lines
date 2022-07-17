<template>
 <AddLineForm :axios='axios'/>
 <LinesTable :axios='axios'/>
</template>

<script>
import axios from 'axios'
import LinesTable from '../components/LinesTable.vue'
import AddLineForm from '../components/AddLineForm.vue'

export default {
  name: 'App',
  components: {
    LinesTable,
    AddLineForm
},
data(){
  return{
    axios: axios
    }
}
}
</script>

<style>
#app {
  font-family: 'Courier New', Courier, monospace, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #08061d;
  margin-top: 60px;
}
</style>
