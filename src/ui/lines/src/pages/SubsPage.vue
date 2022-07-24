<template>
    <div>
<SubsTable :axios='axios'></SubsTable>
    </div>
</template>

<script>
import axios from 'axios'
import SubsTable from '../components/SubsTable.vue'

export default {
  name: 'App',
  components: {
    SubsTable
    },
data(){
  return{
    axios: axios
    }
}
}
</script>

<style scoped>

</style>