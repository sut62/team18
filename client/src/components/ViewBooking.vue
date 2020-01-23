<template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1"
    >View Booking History</v-card-text>
    <v-container>
      <v-layout text-center wrap>
        <v-flex mb-4></v-flex>
      </v-layout>

      <v-row justify="center">
        <v-col cols="12">
          <p></p>
          <v-data-table :headers="headers" :items="items" :items-per-page="10" class="elevation-1"></v-data-table>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
import http from "../http-common";
export default {
  name: "ViewBooking",
  data() {
    return {
      search: "",
      headers: [
        {
          text: "Booking No.",
          align: "center",
          filterable: false,
          value: "id"
        },
        {
          text: "วันที่-เวลา",
          filterable: false,
          value: "booking_time"
        },
        {
          text: "การแสดง",
          align: "left",
          sortable: false,
          value: "chooseShowtime.show.title"
        },
        {
          text: "รอบวันการแสดง",
          align: "center",
          sortable: false,
          value: "chooseShowtime.showDate"
        },
        
        {
          text: "รอบเวลาการแสดง",
          align: "center",
          sortable: false,
          value: "time.time"
        },
        {
          text: "โซน",
          align: "center",
          sortable: false,
          value: "chooseSeat.seatInZone.zone"
        },
        {
          text: "ที่นั่ง",
          align: "center",
          sortable: false,
          value: "chooseSeat.seat_no"
        },
        {
          text: "ราคา (บาท)",
          align: "center",
          sortable: false,
          value: "chooseSeat.seatInZone.price"
        },
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getBookings() {
      http
        .get("/booking")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getBookings();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getBookings();
  }
};
</script>