import { createStore } from "vuex";

export default createStore({
state: ()=> ({
idToEdit:''
}),
getters:{
},
mutations:{
idToEdit(state, id){
    console.log(id+"fromStore")
    state.idToEdit = id;
}
},
actions:{

},

modules:{
    
}

})