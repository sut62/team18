<template>
  <v-app>
    <v-app-bar app class="blue darken-3"
      >
      <v-toolbar-title class="headline text-uppercase">
        
        <span class="font-weight-light white--text"> ระบบจองตั๋วการแสดง</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Username : {{username}}</span>
      </v-btn>
    </v-app-bar>

    <v-content class="blue lighten-3">
     
      <router-view />
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    username: '',
  }),
};
</script>
