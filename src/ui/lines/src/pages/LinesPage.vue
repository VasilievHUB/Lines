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

</style>
