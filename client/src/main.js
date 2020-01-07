import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router';
import VueSimpleAlert from "vue-simple-alert";

Vue.config.productionTip = false
Vue.use(VueSimpleAlert, { reverseButtons: true });

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')

