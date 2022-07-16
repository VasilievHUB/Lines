<template>
<div>
<table border="1px">
<tr><th>ID</th><th class="nameSub">Название</th><th>Подстанция А</th><th>Подстанция B</th><th>Edit</th><th>Delete</th></tr>
<tr v-for="line in this.lines" :key="line.id"><td>{{line.id}}</td><td>{{line.name}}</td><td>{{line.substationA.name}}</td><td>{{line.substationB.name}}</td>
<td><button @click="edit(line.id)">Edit</button></td><td><button @click="remove(line.id)">Remove</button></td></tr>
</table>
</div>
</template>

<script>
import store from '@/store/store'


export default {
  name: 'LinesTable',
  props: ['axios'],
  data(){
    return{
      linesApi: 'http://localhost:8888/lines',
      lines: [],
      name: ''
    }
  },
    created: function(){
 
	this.axios.get(this.linesApi).then(result =>
	this.lines = result.data
	)
},
    methods: {
          remove: function(id){
          this.axios.delete(this.linesApi+'/'+id).then(
            this.lines=this.lines.filter(elem => elem.id != id)
          )
        },
        edit: function(id){
          console.log(id+"fromtable")
          store.commit('idToEdit', id)
          }
    },
 }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
td {
  padding: 0 10px 0 10px;
}
.nameSub {
  width: 600px;
}
#inputSub {
  display: block;
  text-align: left;
  padding-top: '40px';
}
a {
  color: #42b983;
}
</style>
