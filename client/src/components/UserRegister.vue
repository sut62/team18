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
        <font color="yellow-text lighten-1"><v-card-title>Register </v-card-title> </font>
      </v-row>
      </v-img>
<v-form ref="form">
       <v-row justify="center">
             <!-- คำนำหน้า -->
      <v-col cols="4"  >
        
          <v-select 
          label="คำนำหน้า"
                solo
                v-model="userregister.nametypeId"
                :items="nametype"
                item-text="type_name"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาใส่เลือก']"
                required
            
          ></v-select>
        </v-col>
       
         <!-- ชื่อ-นามสกุล -->
        <v-col cols="8"  >
        
           <v-text-field
                solo
                label="ชื่อ-นามสกุล"
                v-model= "name"
                :rules="[(v) => !!v || 'กรุณาใส่ ชื่อ-นามสกุล']"
                required
                clearable
                prepend-icon="mdi-account"
              ></v-text-field>
        </v-col>
       </v-row>
        <!-- email -->

        <v-col class="d-flex mx-auto" cols="12" sm="10" >
        
          <v-text-field
                solo
                label="Email"
                v-model= "email"
                :rules="[(v) => !!v || 'กรุณาใส่ Email']"
                required
                clearable
                prepend-icon="mdi-email"
              ></v-text-field>
        </v-col>

    <!-- โทรศัพท์ -->

        <v-col class="d-flex mx-auto" cols="12" sm="10" >
        
          <v-text-field
                solo
                label="Phone number"
                v-model= "tel"
                :rules="[(v) => !!v || 'กรุณาใส่ เบอร์โทรศัพท์']"
                required
                clearable
                prepend-icon="mdi-phone"
              ></v-text-field>
        </v-col>

         <!-- เพศ --> 
      <v-col class="d-flex mx-auto " cols="12" sm="10" >
        
          <v-select 
          label="เพศ"
                solo
                v-model="userregister.sexId"
                :items="sexs"
                item-text="sex"
                item-value="id"
                :rules="[(v) => !!v || 'กรุณาใส่ เพศ']"
                required
                 prepend-icon="mdi-gender-transgender"
          ></v-select>
        </v-col>
        
        
      
      <!-- question --> 
      <v-col class="d-flex mx-auto " cols="12" sm="10" >
        
          <v-select 
         label="เลือกคำถาม"
                solo
                v-model="userregister.questionId"
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
                 <!-- password -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="PASSWORD"
                v-model= "password"
                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show1 ? 'text' : 'password'"
                hint="At least 8 characters"
                prepend-icon="mdi-lock"
                required
                counter
                clearable
                @click:append="show1 = !show1"
              ></v-text-field>
            </v-col>
          </v-row>
          
        <!-- ยืมยันpassword -->
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                solo
                label="ยืนยัน PASSWORD"
                v-model= "repassword"
                :type="show2 ? 'text' : 'password'"
                :append-icon="show2 ?  'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required,rules.checkpass]"
                hint="At least 8 characters"
                prepend-icon="mdi-lock"
                required
                counter
                clearable
                @click:append="show2 = !show2"
              ></v-text-field>
            </v-col>
          </v-row>
 
         
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
    
  </v-container>
</template>


<script>
import http from "../http-common";

export default {
  name: "userregister",
  data() {
    return {
      userregister: {
        nametypeId: "",
        sexId: "",
        questionId: "",
      },
        valid: false,
        show1: false,
        show2: false,
        name : '',
        email : '',
        tel : '',
        answer : '',
        password : '',
        repassword : '',
        questions : [],
        nametype : [],
        sexs : [],
      rules: {
          required: value => !!value || 'This field is required',
          min: v => v.length >= 8 || 'Min 8 characters',
          checkpass: () => this.repassword == this.password || 'Passwords do not match',
      }
      
    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล NameTppe ใส่ combobox
    getTypeName() {
      http

        .get("/typeName")
        .then(response => {
          this.nametype = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  // ดึงข้อมูล Sex ใส่ combobox
    getSex() {
      http
        .get("/sex")
        .then(response => {
          this.sexs = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
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
  
  
    // function เมื่อกดปุ่ม submit
   saveData() {
    {
      http
        .post(
          "/userregister/" +
            this.userregister.nametypeId +
             "/" +
             this.name +
            "/" +
            this.email + 
            "/" +
            this.tel + 
            "/" +
            this.userregister.sexId +
            "/" +
            this.userregister.questionId +
            "/" +
            this.answer +
            "/" +
            this.password ,
          
            {}
        )
        .then(response => {
          console.log(response);
            alert('บันทึกสำเร็จ')
            this.$router.push("/");

        })
        .catch(e => {
          console.log(e);
          alert('กรุณาใส่ข้อมูลให้ครบถ้วน')
        });
      this.submitted = true;
    }
    
  },
  
    clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getTypeName();
      this.getSex();
      this.getQuestion();
      
    }
    /* eslint-enable no-console */
  },
    mounted() {
      this.getTypeName();
      this.getSex();
      this.getQuestion();
  }
};
</script>
