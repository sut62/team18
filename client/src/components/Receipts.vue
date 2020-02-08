<template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1"
    >RECEIPTS</v-card-text>

    <v-row>
      <v-col cols="18">
        <v-form>
          <v-row justify="center" style="height: 70px;">
            <!-- ชื่อพนักงาน -->
            <v-col cols="3">
              <v-text-field
                solo
                id="emp_name"
                label="ชื่อพนักงาน"
                v-model="name"
                readonly
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" style="height: 70px;">
            <!-- ชื่อลูกค้า -->
            <v-col cols="3">
              <v-select
                id="cu_name"
                label="เลือกชื่อลูกค้า"
                solo
                v-model="selectUser"
                :items="users"
                item-text="name"
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row justify="center" style="height: 70px;">
            <!-- เลือกตั๋วการแสดง -->
            <v-col cols="3">
              <v-select
                id="booking"
                label="เลือกตั๋วการแสดง"
                solo
                v-model="selectBook"
                :items="bookings"
                
                item-value="id"
                :rules="[(v) => !!v || 'Item is required']"
                required
              >
              <template slot="item" slot-scope="data">
                  {{ data.item.chooseShowtime.show.title }} -
                  {{ data.item.chooseShowtime.time.time }} - {{ data.item.chooseSeat.seat_no }}
                </template>

                <template slot="selection" slot-scope="data">
                  {{ data.item.chooseShowtime.show.title }} -
                  {{ data.item.chooseShowtime.time.time }} - {{ data.item.chooseSeat.seat_no }}
                </template>
              </v-select>
            </v-col>
          </v-row>

          <v-row justify="center" style="height: 70px;">
            <!-- เลือกประเภทการชำระเงิน -->
            <v-col cols="3">
              <v-select
                id="payment"
                label="เลือกประเภทการชำระเงิน"
                solo
                v-model="receipts.paymentId"
                :items="payments"
                item-text="type"
                item-value="id"
                :rules="[(v) => !!v || 'This field is required']"
                required
              ></v-select>
            </v-col>
          </v-row>

          <v-row justify="center" style="height: 70px;">
            <!-- กรอกหมายเหตุ -->
            <v-col cols="3">
              <v-textarea
                id = "show_list"
                solo
                label="หมายเหตุ"
                v-model= "note"
                :rules="[(v) => !!v || 'This field is required']"
                required
              ></v-textarea>
            </v-col>    
          </v-row>

          <br><br><br><br>
            <v-col cols="12">
              <v-btn color="red" style="margin-left: 47%;" @click="saveData">save</v-btn>
            </v-col>
        </v-form>
      </v-col>
    </v-row>
  </v-content>
</template>

<script>
import http from "../http-common";

export default {
  watch: {
    selectUser: function(val) {
      this.bookings = [];
      this.getBookings();
    },
  },

  name: "receipts",
  data() {
    return {
      receipts: {
        bookingId: "",
        paymentId: ""
      },
      name: localStorage.getItem("sitePass"),
      bookings: [],
      users: [],
      selectBook: [],
      selectUser: [],
      payments: [],
      note: ""
    };
  },
  methods: {
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

    getPayments() {
      http
        .get("/payment")
        .then(response => {
          this.payments = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getBookings() {
      http
        .get("/booking/" + this.selectUser)
        .then(response => {
          this.bookings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getUsers() {
      http
        .get("/userregister")
        .then(response => {
          this.users = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },


    saveData() {
      http
        .post(
          "/receipts/" +
            localStorage.getItem("empid") +
            "/" +
            this.selectBook +
            "/" +
            this.receipts.paymentId +
            "/" +
            this.note,
          this.receipts
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
          });
        });
      this.submitted = true;
    },
    refreshList() {
      this.getBookings();
      this.getEmployees();
      this.getPayments();
    }
    /* eslint-enable no-console */
  },
  mounted() {
    this.getUsers();
    this.getEmployees();
    this.getPayments();
  }
};
</script>
