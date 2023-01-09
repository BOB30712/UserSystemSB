import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap'
import 'bootstrap-icons/font/bootstrap-icons.css'
import { test, getCookie } from './method/filters'

const app = createApp(App)
app.config.globalProperties.axios = axios
app.config.globalProperties.filters = {
  test,
  getCookie
}
app.use(router)
app.mount('#app')
