import Vue from 'vue'
import Router from 'vue-router';
import Showtime from '../components/Showtime';
import ViewShowtime from '../components/ViewShowtime';
import Login from '../components/Login';
import UserRegister from '../components/UserRegister';
import Booking from '../components/Booking';
import CancelBooking from '../components/CancelBooking';
import Show from '../components/Show';
import Showlist from '../components/Showlist';
import Receipts from '../components/Receipts';
import ViewBooking from '../components/ViewBooking';
import ViewCancelBooking from '../components/ViewCancelBooking';
import SearchReceipts from '../components/SearchReceipts';
import ViewUser from '../components/ViewUser';

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
    },
    {
        path: '/cancel',
        component: CancelBooking
    },
    {
        path: '/show',
        component: Show
    },
    {
        path: '/receipts',
        component: Receipts
    },
    {
        path: '/viewbooking',
        component: ViewBooking
    },
    {
        path: '/viewcancel',
        component: ViewCancelBooking
    },
    {
        path: '/searchreceipts',
        component: SearchReceipts
    },
    {
        path: '/viewuser',
        component: ViewUser
    },
    {
        path: '/viewshowtime',
        component: ViewShowtime 
    },
    {
        path: '/showlist',
        component: Showlist
    }
    ]
});