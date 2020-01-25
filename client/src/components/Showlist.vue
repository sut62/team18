<template>
  <v-content>
    <v-card 
      class="mx-auto "
      max-width="1150"
    >
    <v-card-text
      headline
      align="center"
      class="display-2 font--weight-bold blue darken-3 yellow--text text--lighten-1">SHOW LIST</v-card-text>
     <v-img
        class="white--text align-end"
        height="200px"
        src="https://res.klook.com/images/fl_lossy.progressive,q_65/c_fill,w_1620,h_1080,f_auto/w_80,x_15,y_15,g_south_west,l_klook_water/activities/qibxmjlszioxeqbujpdy/%E0%B8%9A%E0%B8%B1%E0%B8%95%E0%B8%A3%E0%B9%80%E0%B8%82%E0%B9%89%E0%B8%B2%E0%B8%8A%E0%B8%A1%E0%B8%A5%E0%B8%B0%E0%B8%84%E0%B8%A3%E0%B9%80%E0%B8%A7%E0%B8%97%E0%B8%B5%E0%B8%AD%E0%B8%B0%E0%B8%A5%E0%B8%B2%E0%B8%94%E0%B8%B4%E0%B8%99(Aladdin)%E0%B9%83%E0%B8%99%E0%B8%81%E0%B8%A3%E0%B8%B8%E0%B8%87%E0%B8%A5%E0%B8%AD%E0%B8%99%E0%B8%94%E0%B8%AD%E0%B8%99.jpg">
    
     </v-img>
     
    <v-container>
      <v-row justify="center">
        <v-col cols-9 >
                <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1">
                </v-data-table>
        </v-col>  
      </v-row>
   </v-container>
   </v-card> 
  </v-content>
</template>

 <script>
import http from "../http-common";
export default {
  name: "Showlist",
  data() {
    return {


    headers: [{
        
          text: "ชื่อการแสดง",
          align: "center",
          filterable: false,
          value: "title"
        },
        {
          text: "รายชื่อนักแสดงหลัก",
          align: "center",
          filterable: false,
          value: "actor"
        },
        {
          text: "เนื้อเรื่องย่อ",
          align: "center",
          filterable: false,
          value: "information"
        },
                
            ],

            items:[],
    };
  },
methods: {
     /* eslint-disable no-console */
        // ดึงข้อมูล Question ใส่ combobox
    getShows() {
      http
        .get("/show")
        .then(response => {
          this.items = response.data;
         console.log(this.items);
        })
        .catch(e => {

          console.log(e);
        });
    },

    // clear() {
    //   this.$refs.form.reset();
    // },

   
    },
    refreshList() {
      this.getShows();
    //  this.getUser();
    },

    /* eslint-enable no-console */
    mounted() {
      this.getShows();
  }
};
</script>