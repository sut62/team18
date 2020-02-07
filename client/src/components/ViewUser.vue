<template>
  <v-content>
     <v-col justify="center">
           <v-toolbar flat="" class="blue darken-3 yellow--text " >
        <h1>User History</h1> &nbsp;&nbsp;&nbsp;&nbsp;<span>Data : </span> 
        &nbsp;
        <div v-if="this.status">
        <span>have data</span>
        </div>
        &nbsp;
        <div v-if="this.status==false">
        <span>no data</span>
        </div>
       </v-toolbar> 
        <v-data-table :headers="headers" 
        :items="itemsz" :items-per-page="5" class="elevation-1">
        </v-data-table> 

  >
  </v-col>
    <v-container>
      <v-layout text-center wrap>
        <v-flex mb-4></v-flex>
      </v-layout>

      <v-row justify="center">
      <v-col cols="20">
       
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
      itemsz: [],
      status :true
      
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getUserRegister() {
      http
        .get("/userregister")
        .then(response => {
          this.itemsz = response.data;
          console.log(this.itemsz);
          if(this.itemsz==""){
          this.status=false;
        }
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
