import { createApp } from 'vue'
import store from './store/store'
import App from './App.vue'

const app = createApp(App)
app.use(store)
//createApp(App)
.mount('#app')