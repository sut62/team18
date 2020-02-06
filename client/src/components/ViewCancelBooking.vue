<template>
 <v-container class = "blue lighten-3">
   
   

      <v-row justify="center">
        <font color="text-light bg-dark"><v-card-title>     ViewCancelBooking    </v-card-title> </font>
      </v-row>
     

  <v-row justify="center">
        <v-col cols-9 >
                <v-data-table :headers="headers" :items="items" :items-per-page="15" class="elevation-1">
                </v-data-table>

                 <div v-if="alert1">
                <v-alert type="success">พบข้อมูล</v-alert>
              </div>

              <div v-else-if="!alert1">
                <v-alert type="error">ไม่พบข้อมูล</v-alert>
              </div>

        </v-col>  
  </v-row>
 </v-container>
      </template>



      <script>
import http from "../http-common";

export default {
  name: "ViewCancelBooking",
  data() {
    return {


    headers: [{
                    text: " ID ",
                    align: "left",
                    sortable: false,
                    value: "id"
                },
                {text: "date", value: "date" },
                {text: "user", value: "cancelBy.name" },
                {text: "คำตอบ", value: "cancelBy.answer" },
                {text: "การแสดง",value: "cancelBook.chooseShowtime.show.title" },
                {text: "เหตุผล",value: "cancelCaused.reason" },
            ],

            items:[],
    };
  },
methods: {
     /* eslint-disable no-console */
        // ดึงข้อมูล Question ใส่ combobox
    getCancelBook() {
      http
        .get("/ViewCancel")
        .then(response => {
          this.items = response.data;
          if(this.items != "" ){
            this.items = response.data;

            this.alert1 = true;

            console.log(response.data);
            console.log("true");
          }else {
            this.alert1 = false;
            console.log("false");

          }
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
      this.getCancelBook();
    //  this.getUser();
    },

    /* eslint-enable no-console */
    mounted() {
      this.getCancelBook();
  }
};
</script>