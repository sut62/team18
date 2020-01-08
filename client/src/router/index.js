import Vue from 'vue'
import Router from 'vue-router';
import Showtime from '../components/Showtime';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '/',
        component: Showtime
    },
    {
        path: '/view',
        component: Showtime
    }
    ]
});