<template>
  <v-container
        fluid
        fill-height
      >
        <v-layout
          align-center
          justify-center
        >
          <v-flex
            xs12
            sm8
            md4
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="blue darken-3"
                dark
                flat
              >
                <v-toolbar-title class="yellow--text">LOGIN SHOW</v-toolbar-title>
                <v-spacer></v-spacer>
               
               
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                  id = "userName"
                  v-model="username" 
                   
                    label="Login"
                    name="login"
                    
                    type="text"
                  ></v-text-field>

                  <v-text-field
                  v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                   
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn  @click = "getsAdmin" >Admin</v-btn>
                <v-btn  href="/userreg" >Create account</v-btn>
                <v-btn @click = "getsCheck" >Login</v-btn>
                
              </v-card-actions>
          
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
  </template>

<script>
import http from "../http-common";

export default {

  name: "Loing",
  data() {
    return {

      valid:false,
      username: '',
      password: '',
      idz:[],
      ch:"",
      k:""
    };
  },
  methods: {
    emCheck(){
      if(this.username =="admin" && this.password == "1234"){
          this.$router.push("/billing");
      }
    },
    getsCheck() {
      http
        .get("/checkUser/" + this.username + "/" + this.password)
        .then(response => {
          this.idz = response.data;
          console.log(response);
          if (response.data[0] != null) {
              this.ch = this.idz[0].id;
              this.k = this.idz[0].name;
              
              localStorage.setItem("siteId", this.ch);
             localStorage.setItem("siteUser", this.k);
            
            this.$router.push("/booking");
            window.location.reload();
            
          } else {
            alert('รหัสผ่านไม่ถูกต้อง')
          }          
        })
        .catch(e => {
          console.log(e);
        });
    },
    getsAdmin() {
      http
        .get("/checkAdmin/" + this.username + "/" + this.password)
        .then(response => {
          this.idz = response.data;
          console.log(response);
          if (response.data[0] != null) {
              this.ch = this.idz[0].id;
              this.k = this.idz[0].name;
              
              localStorage.setItem("sitePass", this.k);
             //localStorage.setItem("siteUser", this.k);
            
            this.$router.push("/show");
            window.location.reload();
            
          } else {
            alert('รหัสผ่านไม่ถูกต้อง')
          }          
        })
        .catch(e => {
          console.log(e);
        });
    },
     setId() {
      http
        .get("/id/"+ this.username)
        .then(response => {
          this.years = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    
  }
};
</script>
