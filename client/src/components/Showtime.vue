<template>

  <v-container >
    
    <v-card
      class="mx-auto "
      max-width="400"
    >
      <v-img
        class="white--text align-end"
        height="200px"
        src="https://s1.ticketm.net/dam/a/fa1/39f00322-f950-49a8-9ab7-2104f50f0fa1_717401_TABLET_LANDSCAPE_LARGE_16_9.jpg"
        
      >
        <v-card-title>จัดการรอบการแสดง</v-card-title>
      </v-img>
      <br>
      <v-row no-gutters>
      <v-col class="d-flex mx-auto " cols="12" sm="10" >
        
          <v-select 
            id="showtime01"
            v-model="Showtime.showId"
            :items="shows"
            item-text="title"
            item-value="id"
            label="เลือกชื่อการแสดง"
            solo
            
          ></v-select>
        </v-col>
       
        
        <v-col class="d-flex mx-auto" cols="12" sm="10" >
        
          <v-select
            :items="times"
            v-model="Showtime.timeId"
            item-text="time"
            item-value="id"
            label="เลือกเวลาทำการแสดง"
            solo
          ></v-select>
        </v-col>

        <v-col class="d-flex mx-auto" cols="12" sm="10" >
        
          <v-select

            id = "show01"
            v-model="Showtime.locationId"
            :items="locations"
            item-text="location"
            item-value="id"
            label="เลือกสถานที่ทำการแสดง"
            solo
          ></v-select>
        </v-col>
        <!-- เลือกวันเดือนปี -->
        <v-col class="d-flex mx-auto" cols="12" sm="10" >
          <v-menu
          ref="startMenu"
          v-model="startMenu"
          :close-on-content-click="false"
          :nudge-right="40"
          :return-value.sync="start"
          transition="scale-transition"
          min-width="290px"
          offset-y
          full-width
        >
          <template v-slot:activator="{ on }">
            <v-text-field
              v-model="Showtime.start"
              label="เลือกวันที่ทำการแสดง"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="Showtime.start"
            no-title
            scrollable
          >
            <v-spacer></v-spacer>
            <v-btn
              text
              color="primary"
              @click="startMenu = false"
            >
              Cancel
            </v-btn>
            <v-btn
              text
              color="primary"
              @click="$refs.startMenu.save(start)"
            >
              OK
            </v-btn>
          </v-date-picker>
        </v-menu>
          
        </v-col>
        <!-- จบเลือกวันเดือนปี -->
        
      </v-row>
      <v-row justify="center" >
      <v-card-actions >
        <!-- save และแสดงแจ้งเตือน -->
        <v-dialog v-model="dialog2" persistent max-width="290" >
   <template v-slot:activator="{ on }">
        
        <v-btn
          v-on="on"
          @click="saveShowtime()"
          color="indigo darken-3"
          text
        >
          save
        </v-btn>
        
  </template>
      <v-card>
        <v-card-title class="headline">แจ้งเตือน</v-card-title>
        <v-card-text v-if="this.Showtime.statuss2==true">บันทึกข้อมูลสำเร็จ</v-card-text>
        <v-card-text v-if="this.Showtime.statuss2==false">กรุณากรอกข้อมูลให้ถูกต้อง</v-card-text>
        
        
        <v-card-actions>
          <v-spacer></v-spacer>
          
          <v-btn color="green darken-1" text @click="dialog2 = false">ยืนยัน</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- จบ save และแสดงแจ้งเตือน -->
    
    <!-- check และแสดงแจ้งเตือน -->
        <v-dialog v-model="dialog" persistent max-width="290" >
   <template v-slot:activator="{ on }">
        
        <v-btn
          v-on="on"
          @click="findcheck()"
          color="indigo darken-3"
          text
        >
          CHECK
        </v-btn>
        
  </template>
      <v-card>
        <v-card-title class="headline">แจ้งเตือน</v-card-title>
        <v-card-text v-if="this.Showtime.statuss==true">ไม่มีรอบการแสดงซ้ำ</v-card-text>
        <v-card-text v-if="this.Showtime.statuss==null">มีรอบการแสดงซ้ำ</v-card-text>
        <v-card-text v-if="this.Showtime.statuss==false">กรุณากรอกข้อมูลให้ครบ</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          
          <v-btn color="green darken-1" text @click="dialog = false">ยืนยัน</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- จบ check และแสดงแจ้งเตือน -->
  
        
        
        
      </v-card-actions>
      </v-row>
     
    </v-card>

 
     
    
      
    
</v-container>  

</template>

<script>
import http from "../http-common";

export default {
  name: "Showtime",
  data() {
    return {
      Showtime: {
        showId: "",
        timeId: "",
        locationId: "",
        date: "",
        img:'./assets/test.jpg',
        startMenu: false,
        start: '',
        statuss:false,
        statuss2:false
      },
      shows:[],
      times:[],
      locations:[],
      dialog: false,
      dialog2: false
      
      
    };
  },
  methods: {

    // ดึงข้อมูล show ใส่ combobox
    getShows() {
      http
        .get("/show")
        .then(response => {
          this.shows = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล time ใส่ combobox
    getTimes() {
      http
        .get("/time")
        .then(response => {
          this.times = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล location ใส่ combobox
    getLocations() {
      http
        .get("/showlocation")
        .then(response => {
          this.locations = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    findcheck() {
      http
        .get("/showtimeCheck/" +
            this.Showtime.showId +
            "/" +
            this.Showtime.timeId +
            "/" +
            this.Showtime.locationId +
            "/" +
            this.Showtime.start)
        .then(response => {
          console.log(response);
          if (response.data[0] != null) {
            //alert('มีรอบการแสดงซ้ำ')
            this.Showtime.statuss = null;
            
          } else {
            //alert('ไม่มีรอบการแสดงซ้ำ')
            this.Showtime.statuss = true;
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    // function เมื่อกดปุ่ม submit
    saveShowtime() {
      http
        .post(
          "/showtime/" +
            this.Showtime.showId +
            "/" +
            this.Showtime.timeId +
            "/" +
            this.Showtime.locationId +
            "/" +
            this.Showtime.start,
          this.Showtime
        )
        .then(response => {
          console.log(response);
          //this.$router.push("/view");
          //alert('บันทึกรอบการแสดงสำเร็จ')
          if(response.status==200)
          this.Showtime.statuss2 = true;
          else 
            this.Showtime.statuss2 = false;
          
          this.clear();
        })
        .catch(e => {
          console.log(e);
        });
      //this.submitted = true;
    },
    clear(){
      this.$refs.form.reset();
    },
    
    refreshList() {
      this.getShows();
      this.getTimes();
      this.getLocations();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getShows();
    this.getTimes();
    this.getLocations();
  }
};
</script>
