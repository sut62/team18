<template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1"
    >Booking History</v-card-text>
    <v-container>
      <v-layout text-center wrap>
        <v-flex mb-4></v-flex>
      </v-layout>

      <div v-if="this.haveBooking">
        <v-card>
          <v-card-title>
            Username : {{username}}
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              prepend-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :headers="headers"
            :items="items"
            :search="search"
            :items-per-page="10"
            class="elevation-1"
          >
            <template v-slot:item.booking_time="{ item }">{{ formatDate(item.booking_time) }}</template>
          </v-data-table>
        </v-card>
      </div>

      <div v-if="this.noBooking">
        <v-card>
          <v-card-text
            headline
            align="left"
            class="headline font--weight-medium black--text text--accent-4"
          >Username : {{username}}</v-card-text>
          <v-card-text
            align="center"
            class="headline font--weight-medium red--text text--darken-3"
          >ไม่มีการจองตั๋วการแสดงก่อนหน้านี้</v-card-text>
        </v-card>
      </div>
    </v-container>
  </v-content>
</template>

<script>
import http from "../http-common";
import moment from "moment-timezone";
export default {
  name: "ViewBooking",
  data() {
    return {
      search: "",
      headers: [
        {
          text: "Booking No.",
          align: "center",
          sortable: false,
          filterable: false,
          value: "id"
        },
        {
          text: "วันที่-เวลาที่จอง",
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
          sortable: true,
          value: "chooseShowtime.showDate"
        },
        {
          text: "รอบเวลาการแสดง",
          align: "center",
          sortable: true,
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
          sortable: true,
          value: "chooseSeat.seatInZone.price"
        }
      ],
      items: [],
      userId: localStorage.getItem("siteId"),
      username: localStorage.getItem("siteUser"),
      haveBooking: false,
      noBooking: false
    };
  },
  methods: {
    /* eslint-disable no-console */
    getBookings() {
      http
        .get("/bookingby/" + this.userId)
        .then(response => {
          if (response.data[0] != null) {
            this.items = response.data;
            this.haveBooking = true;
          } else {
            this.noBooking = true;
          }
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    formatDate(value) {
      return moment(value).tz('Asia/Bangkok').format('YYYY-MM-DD HH:mm:ss น.')
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