<template>
<NavBar></NavBar>
<div><router-view></router-view></div>
</template>

<script>
import axios from 'axios'
import NavBar from "@/components/NavBar";

export default {
  name: 'App',
  components: {
    NavBar
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
