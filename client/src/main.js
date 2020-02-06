import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router';
import VueSimpleAlert from "vue-simple-alert";
import moment from 'moment-timezone'

Vue.config.productionTip = false

Vue.use(VueSimpleAlert, { reverseButtons: true });

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format("YYYY-MM-DD hh:mm:ss น.")
  }
});

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')

