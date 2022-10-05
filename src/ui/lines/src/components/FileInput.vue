<template>
  <div class="container">
    <div class="large-12 medium-12 small-12 cell">
      <label>File
        <input type="file" id="files" ref="files" multiple v-on:change="handleFileUpload()"/>
      </label>
      <div v-if="this.files.length>1">
      <div>Файлы к загрузке:</div>
      <div v-for="file in this.files" :key="file.name">{{file.name}}</div>
      <button v-on:click="submitFile()">Submit</button>
      </div>
    </div>
  </div>
      <OscChannel v-if="this.showOsc">

    </OscChannel>
</template>
<script>
import axios from 'axios'
import OscChannel from './OscChannel.vue';
  export default {
      name: 'FileInput',
              components:{
            OscChannel
        },
    data(){
      return {
        files: [],
        showOsc: false
      }
    },
    methods: {
      submitFile(){
            let formData = new FormData();
            formData.append('cfg', this.files[0]);
            formData.append('dat', this.files[1]);

                axios.post( 'http://localhost:8888/osc/upload',
                formData,
                {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then(result=>{
              //console.log(result)
              this.$emit('oscGet', result )         
            }
            )
        .catch(function(){
          console.log('FAILURE!!');
        });
      },
      handleFileUpload(){
        for(let i=0; i<this.$refs.files.files.length; i++){
            if(this.$refs.files.files[i].name.toLowerCase().includes('.cfg')){
              console.log(this.$refs.files.files[i].name)
              this.files[i] = this.$refs.files.files[i]
            } else if(this.$refs.files.files[i].name.toLowerCase().includes('.dat')){
              console.log(this.$refs.files.files[i].name)
              this.files[i] = this.$refs.files.files[i]
            } else{
              this.files = []
              alert("Неверный формат файла") 
              break
            } 
            
        }
        if(this.files.length!==2) {
              alert("Выбраны не все файлы. Выберите *.cfg и *.dat файлы")
              console.log(this.files)
              this.files = []
              }
      }
    },

  }
</script>