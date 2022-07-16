<template>
<div>
<table border="1px">
<tr><th>ID</th><th class="nameSub">Название</th><th>Edit</th><th>Delete</th></tr>
<tr v-for="sub in this.substations" :key="sub.id"><td>{{sub.id}}</td><td>{{sub.name}}</td>
<td><button @click="edit">Edit</button></td><td><button @click="remove(sub.id)">Remove</button></td></tr>
</table>
<div id="inputSub">
 <input class="nameSub" type="text" placeholder="Substation name" v-model="name" :name="name"/>
  <input type="button" value="Add" @click="save" />
</div>
</div>
</template>

<script>

//var  substations = [];
export default {
  name: 'SubsTable',
  props: ['axios'],
  data(){
    return{
      substationApi: 'http://localhost:8888/subs',
      substations: [],
      name: ''
    }
  },
    created: function(){
 
	this.axios.get(this.substationApi).then(result =>
	this.substations = result.data
	)
  //this.substations = substations
},
    methods: {
        save: function() {
          var substation = {name: this.name}
                    this.axios.post(this.substationApi, substation).then(result =>
                    this.substations.push(result.data)
                    )
                    this.name = ''
                    },
        remove: function(id){
          console.log(id)
          this.axios.delete(this.substationApi+'/'+id).then(
            this.substations=this.substations.filter(elem => elem.id != id)
          )
          console.log(this.substations)
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
