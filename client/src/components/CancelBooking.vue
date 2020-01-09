<template>
 <v-container class = "blue lighten-3">
   
   <v-card 
      class="mx-auto "
      max-width="500"
    >
     <v-img
        class="white--text align-end"
        height="200px"
        src="https://celebrityaccess.com/wp-content/uploads/2019/09/pexels-photo-2747449-988x416.jpeg"
        
      >
      <v-row justify="center">
        <font color="yellow-text lighten-1"><v-card-title>     CancelBooking    </v-card-title> </font>
      </v-row>
      </v-img>
<v-form ref="form">
       <v-row justify="center">
         
      
      
      <!-- question --> 
      <v-col class="d-flex mx-auto " cols="12" sm="10" >
        
          <v-select 
         label="เลือกคำถาม"
                solo
                v-model="cancelBooking.questionId"
                :items="questions "
                item-text="question"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาใส่ เลือกคำถามยืนยัน']"
                required
                prepend-icon="mdi-comment-question-outline"
          ></v-select>
        </v-col>
     
       <!-- answer -->

        <v-col class="d-flex mx-auto" cols="12" sm="10" >
        
          <v-text-field
                solo
                label="Answer"
                v-model= "answer"
                :rules="[(v) => !!v || 'กรุณาใส่ ตอบคำถามการยืนยัน']"
                required
                clearable
                prepend-icon="mdi-comment-question-outline"
              ></v-text-field>
        </v-col>
          </v-row>
        <!-- check -->
        
     <v-col justify = "center">
           <v-row justify="center">
       <v-btn
          @click="check()"
          color="indigo darken-3"
          text
        >
          check
        </v-btn>
           </v-row>
     </v-col>

        <!-- Booking -->
        <br>
        <v-col class="d-flex mx-auto" cols="12" sm="10" > 
          <v-select 
         label="เลือกตั๋วการแสดง"
                solo
                v-model="cancelBooking.bookingId"
                :items="bookings "
                item-text="booking_time"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาเลือกตั๋วการแสดง']"
                required
                prepend-icon="mdi-comment-question-outline"
          ></v-select>
        </v-col>

        <!-- CancelReason -->

        <v-col class="d-flex mx-auto" cols="12" sm="10" > 
          <v-select 
         label="เลือกเหตุผล"
                solo
                v-model="cancelBooking.reasonId"
                :items="reasons "
                item-text="reason"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาเลือกเหตุผล']"
                required
                prepend-icon="mdi-comment-question-outline"
          ></v-select>
        </v-col>




          
 
         
      <v-row justify="center" >
      <v-card-actions >
        <v-btn
          @click="saveData()"
          color="indigo darken-3"
          text
        >
          SAVE
        </v-btn>
  
        <v-btn
          @click="clear()"
          color="indigo darken-3"
          text
        >
          CLEAR
        </v-btn>
      </v-card-actions>
      </v-row>

  


        </v-form> 
            </v-card>
    <p>{{this.cancelBooking.questionId}}</p>
    <p>{{this.answer}}</p>
    <p>{{this.cancelBooking.bookingId}}</p>
    <p>{{this.cancelBooking.reasonId}}</p>
  </v-container>
</template>


<script>
import http from "../http-common";

export default {
  name: "cancelBooking",
  data() {
    return {
      cancelBooking: {
        questionId: "",
        reasonId: "",
        bookingId: "",
      },
      //  valid: false,
        answer : '',
        User : [],
        questions : [],
        reasons : [],
        bookings : [],
        users:[],
        a : localStorage.getItem("siteId"),
      
    };
  },
  methods: {
    /* eslint-disable no-console */

   
        // ดึงข้อมูล Question ใส่ combobox
    getQuestion() {
     
      http
        .get("/question")
        .then(response => {
          this.questions = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
        // ดึงข้อมูล Reason ใส่ combobox
    getReason() {
      http
        .get("/cancelReason")
        .then(response => {
          this.reasons = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  
    // ดึงข้อมูล Booking ใส่ combobox
    getBooking() {
      http
        .get("/booking")
        .then(response => {
          this.bookings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


  //กดปุ่ม check
    check(){
      http
        .get("/userregister/id="+this.a)
        .then(response => {
          this.User = response.data;
          console.log(this.User[0].question.id  +"-------"+this.User[0].answer +" xxx "+ this.answer);
           if(this.User[0].question.id == this.cancelBooking.questionId && this.User[0].answer == this.answer){
             alert("ยืนยันสำเร็จ")
              this.getReason();
               this.getBooking();
       }
        })
        .catch(e => {
          console.log(e);
        });
     
},
  
    // function เมื่อกดปุ่ม save
   saveData() {
    {
      http
        .post(
          "/cancelbooking/" +
            this.cancelBooking.bookingId +
            "/" +
            this.cancelBooking.reasonId +
            "/"+
            this.a,
            {}
            
        )
   //   alert("บันทึกสำเร็จ")
      
        .catch(e => {
          console.log(e);
        });
      
    }
    
  },
    clear() {
      this.$refs.form.reset();
            

        
    },
    refreshList() {
      this.getQuestion();
      this.getReason();
      this.getBooking();
      this.getUser();
    }
    /* eslint-enable no-console */
  },
    mounted() {
      this.getQuestion();
    
  }
};
</script>
