<template>
<div>
    <div v-if="this.id">Редактируется Линия: ID: ({{this.id}}) \/ название: ({{this.lineName}}) <input @click="rejectEdit" type="button" value="Отмена"></div>
    <div v-else>Добавление новой линии</div>
<table border="1px">
    <tr><td colspan="2">Название:<input v-model="lineName" size="50"><input type="button" value="create" @click="constructLineName"></td></tr>
<tr><td>ПС A:</td><td><select v-model="selectedA">
<option disabled value="">Выберете ПС А</option>
<option v-for="sub in substations" :key="sub.id" :value="sub.id">{{sub.name}}</option>
</select></td></tr>
<tr><td>ПС B:</td><td><select v-model="selectedB">
<option disabled value="">Выберете ПС B</option>
<option v-for="sub in substations" :key="sub.id" :value="sub.id">{{sub.name}}</option>
</select></td></tr>
<tr><td>Класс напряжения, кВ:</td><td><select v-model="lineVoltage"><option v-for="v in voltage" :key="v.k" :value="v.k">{{v.view}}</option></select></td></tr>
<tr><td>Тип:</td><td><select v-model="lineType"><option v-for="t in type" :key="t.k" :value="t.k">{{t.view}}</option></select></td></tr>
</table>

<table v-for="zone in zones" :key="zone.id" border="1px">
<tr><td colspan="2">Zone#{{zone.number}}</td><td colspan="2">distance=<input v-model="zone.distance"> км</td></tr>
<tr><td>X0=<input v-model="zone.x0"></td><td>R0=<input v-model="zone.r0"></td><td>X1=<input v-model="zone.x1"></td><td>R1=<input v-model="zone.r1"></td></tr>
<tr></tr>
</table>
<button @click="addZone">Add Zone</button>
<button @click="deleteZone" v-if="this.zones.length">Del Zone</button>
<div><button @click="saveLine">Save line in DB</button></div>

</div>
</template>

<script>
import store from '@/store/store'

export default{
    name: 'AddLineForm',
    props: ['axios',],
    data(){
        return{
            substationApi: 'http://localhost:8888/subs',
            linesApi: 'http://localhost:8888/lines',
            substations: [],
            zones: [],
            currentZone: {
                distance: '',
                x0:'',
                r0:'',
                x1:'',
                r1:'',
                number: 1
            },
            selectedA:0,
            selectedB:0,
            subNameA:'',
            subNameB:'',
            lineName: '',
            lineVoltage: '',
            lineType: '',
            voltage: [{k: 'V_220', view:'220'},{k: 'V_110', view:'110'}],
            type: [{k: 'VL', view:'ВЛ'},{k: 'KL', view:'КЛ'}, {k: 'KVL', view:'КВЛ'}],
            //id: ''
        }
    },
    created: function(){
         this.axios.get(this.substationApi).then(result =>
         this.substations = result.data
        )
        
    },
    methods: {
        deleteZone(){
           this.zones.pop()
      },
      addZone(){
          function сurrentZone() {
                this.distance = ''
                this.x0 = ''
                this.r0 = ''
                this.x1 = ''
                this.r1 = ''
                this.number = ''}
            var zoneToAdd = new сurrentZone()    
            zoneToAdd.number = this.zones.length + 1
            this.zones.push(zoneToAdd)
      },
       saveLine(){
           var lineToSave = {
               substationA: {id:this.selectedA},
               substationB: {id:this.selectedB},
               distances: this.zones,
               name: this.lineName,
               id: this.id,
               lineType: this.lineType,
               voltageLevel: this.lineVoltage
                }
               this.axios.post(this.linesApi, lineToSave).then(result=>
           alert(result.data.id))
       },
       constructLineName(){
             this.lineName= this.type.find(el=>el.k == this.lineType).view+' '+this.voltage.find(el=>el.k==this.lineVoltage).view+' кВ '+
             this.substations.find(el=>el.id==this.selectedA).name.split(' ')[3]+' - '+this.substations.find(el=>el.id==this.selectedB).name.split(' ')[3]
       },
       rejectEdit(){
        store.state.idToEdit=''
       }
    },
    watch:{
        id(oldVal){
            console.log(1)
            
            if(oldVal){
                var lineToEdit;
                this.axios.get(this.linesApi + '/'+oldVal).then(result=>{
                lineToEdit=result.data  
                this.selectedA=lineToEdit.substationA.id; 
                this.selectedB=lineToEdit.substationB.id;
                if(lineToEdit.distances){
                this.zones=lineToEdit.distances;}
                this.lineName=lineToEdit.name;
                this.lineType=lineToEdit.lineType,
                this.lineVoltage=lineToEdit.voltageLevel
                }
                             
                )   
                
            }
        }
    },
    computed:{
          id: {
    get() {
      return store.state.idToEdit
    }
  }
    }


}
</script>

<style scoped>
table {
    width: 1000px;
    columns: auto;
}
button {
    text-align: left;
}
</style>