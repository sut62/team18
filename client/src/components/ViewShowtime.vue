<template>
<v-container>
     
      <v-col justify="center">
           <v-toolbar flat="" class="blue darken-3 yellow--text " >
        <h1>SHOW TIME</h1> &nbsp;&nbsp;&nbsp;&nbsp;<span>Data : </span> 
        &nbsp;
        <div v-if="this.status">
        <span>have data</span>
        </div>
        &nbsp;
        <div v-if="this.status==false">
        <span>no data</span>
        </div>
       </v-toolbar>  
        <v-data-table
    
    :headers="headers"
    :items="itemsz"
    
    class="elevation-1"
  >
  <template v-slot:item.show.ratingshow.rate="{ item }">
      <v-chip :color="getColor(item.show.ratingshow.rate)" dark>{{ item.show.ratingshow.rate }}</v-chip>
    </template> 
  </v-data-table>
</v-col>
 </v-container>
 
 </template>
<script>
import http from "../http-common";
export default {
    name:"ShowtimeData",
    data(){
        return{
          headers: [
          {
            text: "ชื่อการแสดง",
            align: "left",
            sortable: false,
            value: "show.title",
          },
          { text: 'เวลา', value: 'time.time' },
          { text: 'วันที่แสดง', value: 'showDate' },
          { text: 'เรทติ้ง', value: 'show.ratingshow.rate' },
         
        ],
        itemsz:[],
        status :true
        };
        
    },
methods:{
   getColor (calories) {
        if (calories == "อายุ 18ปีขึ้นไป") return 'red'
        else if (calories =="อายุ 13ปีขึ้นไป") return 'orange'
        else return 'green'
      },
   getShowtimeData(){
       http
    .get("/showtime")
    .then(response =>{
        this.itemsz = response.data;
        console.log(this.itemsz);
        if(this.itemsz==""){
          this.status=false;
        }
    })
    .catch(e =>{
        console.log(e);
    });
   },
   refreshList(){
       this.getShowtimeData();
   }
   
},

mounted(){
 this.getShowtimeData();
}

}

</script>


