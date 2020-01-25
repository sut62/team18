<template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-2 font--weight-bold blue darken-3 yellow--text text--lighten-1"
    >View User History</v-card-text>
    <v-container>
      <v-layout text-center wrap>
        <v-flex mb-4></v-flex>
      </v-layout>

      <v-row justify="center">
      <v-col cols="20">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
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
      
      headers: [
        {
          text: "User No.",
          align: "center",
          sortable: false,
          filterable: false,
          value: "id"
        },
        { text: "NameType", value: "typeName.type_name" },
        { text: "Name", value: "name" },
        { text: "Email", value: "email" },
        { text: "Sex", value: "sex.sex" },
         { text: "phone", value: "tel" },
        { text: "Question", value: "question.question" },
        { text: "Answer", value: "answer" },
        { text: "Password", value: "password" },
         { text: "Date", value: "date" },
       
      ],
      items: [],
      
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getUserRegister() {
      http
        .get("/userregister")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getUserRegister();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getUserRegister();
  }
};
</script>
