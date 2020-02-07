<template>
  <v-content>
      <v-card-text
      headline
      align= "center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1">Show Management</v-card-text>
    <v-container>
    <v-row>
    <v-col cols="18">
    <v-form>
            <v-row justify="center" style = "height: 65px;">
            <!-- ชื่อพนักงาน -->
              <v-col cols="3">
                <v-text-field
                solo
                id="emp_name"
                label="ชื่อพนักงาน"
                v-model="name"
                readonly
                prepend-icon="mdi-account"
                ></v-text-field>
              </v-col>
              </v-row>

              <v-row justify="center" style = "height: 65px;">
              <v-col cols="3">
              <v-text-field
                id = "showname"
                solo
                label="ชื่อการแสดง"
                v-model="title"
                :rules="[(v) => !!v || 'This field is required']"
                required
                prepend-icon="mdi-lead-pencil"
              ></v-text-field>
            </v-col>
            </v-row>

          

          <v-row justify="center" style = "height: 65px;">
            <!-- ประเภทการแสดง -->
              <v-col cols="3">
                <v-select
                  id = "type_show"
                  label="ประเภทการแสดง"
                  solo
                  v-model="show.showtypeId"
                  :items="showtypes"
                  item-text="shname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-play-circle"
                ></v-select>
              </v-col>
              </v-row>

              <v-row justify="center" style = "height: 65px;">
              <!-- เรทการแสดง -->
              <v-col cols="3">
                <v-select
                  id = "rate_show"
                  label="เรทการแสดง"
                  solo
                  v-model="show.ratingshowId"
                  :items="ratingshows"
                  item-text="rate"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                  prepend-icon="mdi-account-plus"
                ></v-select>
              </v-col>
            </v-row>

            <v-row justify="center" style = "height: 65px;">
            <!-- กรอกรายชื่อนักแสดง -->
            <v-col cols="3">
              <v-text-field
                id = "actor"
                solo
                label="รายชื่อนักแสดงหลัก"
                v-model="actor"
                :rules="[(v) => !!v || 'This field is required']"
                required
                prepend-icon="mdi-account"
              ></v-text-field>
            </v-col>
            </v-row>   
        

            <v-row justify="center" style = "height: 65px;">
            <!-- กรอกเนื้อเรื่องย่อ -->
            <v-col cols="3">
              <v-textarea
                id = "show_list"
                solo
                label="เนื้อเรื่องย่อ"
                v-model="information"
                :rules="[(v) => !!v || 'This field is required']"
                required
                prepend-icon="mdi-account-card-details"
              ></v-textarea>
            </v-col>    
          </v-row>


          <br><br><br><br>
            <v-col cols="12">
              <v-btn color="red" style="margin-left: 48%;" @click="saveData">save</v-btn>
            </v-col>
          
        </v-form>
      </v-col>
    </v-row>
    </v-container>
 
  </v-content>
</template>

<script>
import http from "../http-common";

export default {
  name: "show",
  data() {
    return {
      show: {
        ratingshowId: "",
        showtypeId: "",
      },
        actor : "",
        title : "",
        information : "",
        name: localStorage.getItem("sitePass"),
        ratingshows : "",
        showtypes : "",
    };
  },

  methods: {
    // ดึงข้อมูล Employee ใส่ combobox
    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // ดึงข้อมูล Ratingshow ใส่ combobox
    getRatingshows() {
      http
        .get("/ratingshow")
        .then(response => {
          this.ratingshows = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล Showtype ใส่ combobox
    getShowtypes() {
      http
        .get("/showtype")
        .then(response => {
          this.showtypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  
  
    // function เมื่อกดปุ่ม save
    saveData() {
      http
        .post(
          "/show/" +
            this.actor +
            "/" +
            localStorage.getItem("empid") +
            "/" +
            this.information +
            "/" +
            this.show.ratingshowId +
            "/" +
            this.show.showtypeId +
            "/" +
            this.title,
        
            this.show
        )
        .then(response => {
          console.log(response);
          this.$fire({
            title: "บันทึกข้อมูลสำเร็จ",
            type: "success"
          }).then(r => {
            console.log(r.value);
            window.location.reload();
          });
        })
        .catch(e => {
          console.log(e);
          this.$fire({
            title: "บันทึกข้อมูลไม่สำเร็จ",
            type: "error"
          })
        });
      this.submitted = true;
    },
    refreshList() {
      this.getEmployees();
      this.getRatingshows();
      this.getShowtypes();
    }
    /* eslint-enable no-console */
  },
    
  
    mounted() {
     
      this.getEmployees();
      this.getRatingshows();
      this.getShowtypes();
  }
};
</script>


