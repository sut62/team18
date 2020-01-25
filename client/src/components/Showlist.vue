<template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1">SHOW LIST</v-card-text>
    <v-container>
      <v-row justify="center">
        <v-col cols-9 >
                <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1">
                </v-data-table>
        </v-col>  
      </v-row>
   </v-container>
  </v-content>
</template>

 <script>
import http from "../http-common";
export default {
  name: "Showlist",
  data() {
    return {


    headers: [{
        text: "Show No.",
        align: "left",
        sortable: true,
        value: "id"
        },
        {
          text: "Show",
          align: "left",
          filterable: false,
          value: "title"
        },
        {
          text: "Main Actors",
          align: "left",
          filterable: false,
          value: "actor"
        },
        {
          text: "Story",
          align: "left",
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