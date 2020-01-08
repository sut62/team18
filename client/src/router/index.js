import Vue from 'vue'
import Router from 'vue-router';
import Showtime from '../components/Showtime';
import Login from '../components/Login';
import UserRegister from '../components/UserRegister';
import Booking from '../components/Booking';

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '/',
        component: Login
    },
    {
        path: '/showtime',
        component: Showtime
    },
    {
        path: '/userreg',
        component: UserRegister
    },

    {
        path: '/booking',
        component: Booking
    }
    ]
});