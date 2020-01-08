<template >

  <v-app >
    <div>
<v-app-bar app class ="blue darken-3" >
    
      <v-toolbar-title v-if="this.ids != null" class="headline text-uppercase">
        
        <span class="font-weight-light"> ระบบลงการแสดง</span>
        &nbsp;
        <span>{{this.ids}}</span>
          
         <v-btn text href="/booking/" target="_self">Booking</v-btn>
          <v-btn text href="/register" target="_self">Register</v-btn>
           <v-btn text href="/drop" target="_self">DropEnroll</v-btn>
            <v-btn text href="/change" target="_self">ChangeGroup</v-btn>
            <v-btn @click="logOut" text target="_self">LOGOUT</v-btn>
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
    ids: localStorage.getItem("siteUser")
    };
  },
  methods:{
    logOut(){
      localStorage.removeItem("siteId");
      localStorage.removeItem("siteUser");
      this.ids=null;
      this.$router.push("/");
},
  }
   };
</script>
