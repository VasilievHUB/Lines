<template>
    <FileInput v-on:oscGet="osc=$event"></FileInput>
    <div v-if="this.osc">
    <OscChannel v-for="channel in this.osc.data.channels" :key="channel.name"
        :channelKey="channel.name"
        :chartData="{
             labels:this.osc.data.timePoints,
             datasets:[{
                        data:channel.values
                    }]
                }">
    </OscChannel>
</div>
</template>

<script>
import FileInput from '@/components/FileInput.vue'
import OscChannel from '@/components/OscChannel.vue';
    export default {
        components:{
            FileInput,
            OscChannel
        },
        props:{
            
        },
        data() {
            return {
                chartData:{
                    labels:[],
                    datasets:[{
                        data:[]
                    }]
                },
                osc:''
                
            }
        },
        methods:{

        }

    }
</script>

<style scoped>

</style>