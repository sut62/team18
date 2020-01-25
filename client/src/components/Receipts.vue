<template>
  <v-content>
      <v-card-text
      headline
      align= "center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1">RECEIPTS</v-card-text>

    <v-row>
    <v-col cols="18">
    <v-form>
      
            <v-row justify="center" style = "height: 70px;">
            <!-- ชื่อพนักงาน -->
              <v-col cols="3">
                <v-select
                  id = "emp_name"
                  label="เลือกชื่อพนักงาน"
                  solo
                  v-model="receipts.employeeId"
                  :items="employees"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

          <v-row justify="center" style = "height: 70px;">
            <!-- ชื่อลูกค้า -->
              <v-col cols="3">
                <v-select
                  id = "cus_name"
                  label="เลือกชื่อลูกค้า"
                  solo
                  v-model="receipts.bookingId"
                  :items="bookings"
                  item-text="chooseUser.name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

          <v-row justify="center" style = "height: 70px;">
            <!-- เลือกตั๋วการแสดง -->
              <v-col cols="3">
                <v-select
                  id = "booking"
                  label="เลือกตั๋วการแสดง"
                  solo
                  v-model="receipts.bookingId"
                  :items="bookings"
                  item-text="chooseShowtime.show.title"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

          <v-row justify="center" style = "height: 70px;">
            <!-- เลือกประเภทการชำระเงิน -->
              <v-col cols="3">
                <v-select
                  id = "payment"
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

          <v-row justify="center" style = "height: 45px;">
            <v-col cols="12">
              <v-btn color="red" style="margin-left: 45%;" @click="saveData">save</v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-col>
    </v-row>
  </v-content>
</template>

<script>
import http from "../http-common";

export default {
  name: "receipts",
  data() {
    return {
      receipts: {
        bookingId: "",
        employeeId: "",
        paymentId: ""
      },
      employees: [],
      bookings: [],
      payments: [],
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
        .get("/booking")
        .then(response => {
          this.bookings = response.data;
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
            this.receipts.bookingId +
            "/" +
            this.receipts.employeeId +
            "/" +
            this.receipts.paymentId,
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
          })
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
    this.getBookings();
    this.getEmployees();
    this.getPayments();
  }
};
</script>
