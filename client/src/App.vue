<template >

  <v-app >
    <div>
<v-app-bar app class ="blue darken-3" >
    
      <v-toolbar-title v-if="this.ids != null" class="headline text-uppercase">
        
        <span class="font-weight-light white--text"> ระบบจองตั๋วการแสดง</span>
        &nbsp;
        <v-btn text href="/booking/" target="_self" class="white--text">Booking</v-btn>
        <v-btn text href="/cancel" target="_self" class="white--text">Cancel Booking</v-btn>
        <v-btn text href="/viewbooking/" target="_self" class="white--text">Booking History</v-btn>
        <v-btn text href="/viewshowtime/" target="_self" class="white--text">Showtime History</v-btn>
        <v-btn text href="/showlist/" target="_self" class="white--text">Show List</v-btn>
        
        <v-btn @click="logOut" text target="_self" class="white--text">LOGOUT    </v-btn>
        <span class="white--text" >{{this.ids}}</span>
        </v-toolbar-title>


        <v-toolbar-title v-if="this.nm != null" class="headline text-uppercase">
        
        <span class="font-weight-light white--text"> ระบบจองตั๋วการแสดง</span>
        &nbsp;
        <v-btn text href="/show/" target="_self" class="white--text">Show</v-btn>
        <v-btn text href="/showtime" target="_self" class="white--text">Showtime</v-btn>
        <v-btn text href="/receipts" target="_self" class="white--text">Receipts</v-btn>
        <v-btn text href="/searchreceipts" target="_self" class="white--text">Search Receipts</v-btn>
        <v-btn text href="/viewuser/" target="_self" class="white--text">Register History</v-btn>
        <v-btn text href="/viewcancel/" target="_self" class="white--text">View CancelBooking</v-btn>
        <v-btn @click="logOut" text target="_self" class="white--text">LOGOUT    </v-btn>
        <span class="white--text" >{{this.nm}}</span>
        </v-toolbar-title>


        
   </v-app-bar>
    </div>
<v-content class="blue lighten-3">
     
      <router-view />
    </v-content>
  </v-app>
  
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      valid:false,
    check: false,
    ids: localStorage.getItem("siteUser"),
    nm: localStorage.getItem("sitePass")
    };
  },
  methods:{
    logOut(){
      localStorage.removeItem("siteId");
      localStorage.removeItem("siteUser");
      localStorage.removeItem("sitePass");
      this.ids=null;
      this.nm=null;
      this.$router.push("/");
},

  }
   };
</script>
