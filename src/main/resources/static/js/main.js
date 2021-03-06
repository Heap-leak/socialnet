import Vue from 'vue'
import Vuetify from 'vuetify'
import 'api/resource'
import App from 'pages/App.vue'
import { connect} from '../util/ws'
import 'vuetify/dist/vuetify.min.css'
import '@babel/polyfill'
import store from 'store/store'
import router from 'router/router'

if(frontData.profile) {
    connect();
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    router,
    store,
    render: a => a(App)
})