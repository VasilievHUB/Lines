import { createStore } from "vuex";

export default createStore({
state: ()=> ({
idToEdit:'',
osc:{
    values: [],
    timePoints:[]
}
}),
getters:{
},
mutations:{
idToEdit(state, id){
    state.idToEdit = id;
},
osc(state, osc){
    state.osc = osc;
}

},
actions:{

},

modules:{
    
}

})