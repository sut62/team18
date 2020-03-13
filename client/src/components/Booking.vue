 <template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1"
    >Booking Ticket</v-card-text>
    <v-container>
      <v-layout text-center wrap>
        <v-flex mb-4>
          <v-col></v-col>

          <!-- show -->
          <v-form v-model="validshow" ref="form">
            <v-row justify="center">
              <v-col cols="auto">
                <p class="blue-grey--text text--darken-4">เลือกการแสดง</p>
                <v-select
                  id="b-show"
                  label="การแสดง"
                  solo
                  v-model="booking.showId"
                  :items="shows"
                  item-text="title"
                  item-value="id"
                  hide-selected
                  :rules="[(v) => !!v || '*กรุณาเลือกการแสดง']"
                  required
                ></v-select>
              </v-col>
            </v-row>
          </v-form>

          <!-- button show showtime -->
          <v-btn
            rounded
            dark
            @click="showShowtime"
            :class="{ error : !validshow, success: validshow }"
          >ค้นหารอบการแสดง</v-btn>

          <!-- dialog showtime -->
          <v-dialog v-model="getShowtimeCheck" persistent max-width="500px">
            <v-card color="grey lighten-3">
              <v-card-title>
                {{showname}}
                <v-col align="right">
                  <v-btn
                    outlined
                    color="red"
                    large
                    class="red--text"
                    @click="getShowtimeCheck = !getShowtimeCheck"
                  >X</v-btn>
                </v-col>
              </v-card-title>

              <!-- showtime -->
              <v-form v-model="validshowtime" ref="form">
                <v-row justify="center">
                  <v-col cols="auto">
                    <v-select
                      id="b-showtime"
                      label="รอบวันการแสดง"
                      solo
                      v-model="booking.showtimeId"
                      :items="showtimes"
                      item-text="showDate"
                      item-value="id"
                      hide-selected
                      :rules="[(v) => !!v || '*กรุณาเลือกรอบวันการแสดง']"
                      required
                    ></v-select>
                    <v-col>
                      <div align="center">
                        <v-btn
                          rounded
                          @click="showDatetime"
                          :class="{ error: !validshowtime, success: validshowtime }"
                        >ค้นหาเวลาการแสดง</v-btn>
                      </div>
                    </v-col>
                  </v-col>
                </v-row>
              </v-form>

              <!-- time -->
              <div v-if="this.getTimeCheck">
                <v-form v-model="validtime" ref="form">
                  <v-row justify="center">
                    <v-col cols="auto">
                      <v-select
                        id="b-time"
                        label="รอบเวลาการแสดง"
                        solo
                        v-model="booking.timeId"
                        :items="showtimesDate"
                        item-text="time.time"
                        item-value="id"
                        :rules="[(v) => !!v || '*กรุณาเลือกรอบเวลาการแสดง']"
                        required
                      ></v-select>

                      <!-- button show zone -->
                      <v-col>
                        <div align="center">
                          <v-btn
                            rounded
                            @click="showZone"
                            :class="{ error: !validtime, success: validtime }"
                          >ค้นหาโซน</v-btn>
                        </div>
                      </v-col>
                    </v-col>
                  </v-row>
                </v-form>

                <!-- zone -->
                <div v-if="this.getZoneCheck">
                  <v-form v-model="validzone" ref="form">
                    <v-row justify="center">
                      <v-col cols="auto">
                        <v-select
                          id="b-zone"
                          v-model="booking.zoneId"
                          :items="zones"
                          item-text="zone"
                          item-value="id"
                          label="โซน"
                          hide-selected
                          solo
                          :rules="[(v) => !!v || '*กรุณาเลือกโซน']"
                        ></v-select>

                        <!-- button show seat-->
                        <div align="center">
                          <v-btn
                            rounded
                            @click="showSeat"
                            :class="{ error: !validzone, success: validzone }"
                          >ค้นหาที่นั่ง</v-btn>
                        </div>
                      </v-col>
                    </v-row>
                  </v-form>
                  <v-col></v-col>

                  <!-- seat -->
                  <div v-if="this.getSeatCheck">
                    <v-form v-model="validseat" ref="form">
                      <v-row justify="center">
                        <v-col cols="auto">
                          <v-select
                            id="b-seat"
                            label="ที่นั่ง"
                            solo
                            v-model="booking.seatId"
                            :items="seats"
                            item-text="seat_no"
                            item-value="id"
                          ></v-select>
                        </v-col>
                      </v-row>
                    </v-form>

                    <!-- submit -->
                    <div align="center">
                      <v-btn dark large rounded @click="getInfo()" class="primary">ยืนยันการจองตั๋ว</v-btn>
                    </div>
                  </div>
                </div>
              </div>
            </v-card>
          </v-dialog>

          <!-- confirm get info -->
          <v-dialog v-model="getConfirm" persistent max-width="500px">
            <v-card>
              <v-row justify="center">
                <v-col cols="auto">
                  <blockquote>
                    <br />บัญชีผู้ใช้ :
                    <span class="indigo--text text--darken-4">{{username}}</span>
                    <br />การแสดง :
                    <span class="indigo--text text--darken-4">{{showname}}</span>
                    <br />รอบวันการแสดง :
                    <span class="indigo--text text--darken-4">{{showDateString}}</span>
                    <br />รอบเวลาการแสดง :
                    <span class="indigo--text text--darken-4">{{timeString}}</span>
                    <br />โซน :
                    <span class="indigo--text text--darken-4">{{zone}}</span>
                    <br />ที่นั่ง :
                    <span class="indigo--text text--darken-4">{{seat}}</span>
                    <br />จำนวน :
                    <span class="indigo--text text--darken-4">1 ที่นั่ง</span>
                    <br />ราคา :
                    <span class="indigo--text text--darken-4">{{price}} บาท</span>
                    <br />
                    <br />
                    <div align="center" class="display-1 font-weight-bold">ยืนยันการจองตั๋ว?</div>
                  </blockquote>
                  <v-row justify="center">
                    <v-col cols="auto">
                      <v-btn dark large rounded @click="checkSeat" class="success">ยืนยัน</v-btn>
                      <v-btn
                        dark
                        large
                        rounded
                        @click="getConfirm = !getConfirm"
                        class="error"
                      >ยกเลิก</v-btn>
                    </v-col>
                  </v-row>
                </v-col>
              </v-row>
            </v-card>
          </v-dialog>
        </v-flex>
      </v-layout>
    </v-container>
  </v-content>
</template>

<script>
import http from "../http-common";

export default {
  name: "booking",
  data() {
    return {
      booking: {
        userId: localStorage.getItem("siteId"),
        showId: "",
        showtimeId: "",
        zoneId: "",
        seatId: "",
        timeId: ""
      },
      shows: [],
      showtimes: [],
      showtimesDate: [],
      seats: [],
      zones: [],
      validzone: false,
      validshow: false,
      validtime: false,
      validshowtime: false,
      getSeatCheck: false,
      getZoneCheck: false,
      getTimeCheck: false,
      getShowCheck: false,
      getShowtimeCheck: false,
      getConfirm: false,
      username: localStorage.getItem("siteUser"),
      showname: "",
      showtime: "",
      timeString: "",
      zone: "",
      price: "",
      seat: "",
      showDateString: ""
    };
  },
  methods: {
    /* eslint-disable no-console */
    disUser() {
      http
        .get("/user/userid=" + this.booking.userId)
        .then(response => {
          this.username = response.data.name;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

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

    disShow() {
      http
        .get("/show/showid=" + this.booking.showId)
        .then(response => {
          this.showname = response.data.title;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      this.disUser();
    },

    getShowtimes() {
      http
        .get("/showtimeD/showtimeid=" + this.booking.showId) /////////////
        .then(response => {
          this.showtimes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      this.disShow();
      this.disShowtime();
    },

    showDatetime() {
      if (this.validshowtime) {
        http
          .get("/showtime/showtimeid=" + this.booking.showtimeId)
          .then(response => {
            this.showDateString = response.data[0].showDate;
            this.timeString = response.data[0].time.time;
            this.getTimeCheck = true;
            console.log(response.data);
            this.LastTime();
          })
          .catch(e => {
            console.log(e);
          });
      } else {
        this.$fire({
          title: "กรุณากรอกข้อมูลให้ครบถ้วน!",
          text: "กรุณาเลือกรอบวันการแสดงก่อนทำรายการต่อไป",
          type: "error"
        }).then(r => {
          console.log(r.value);
        });
      }
    },

    LastTime() {
      http
        .get("/showDatetime/showid=" + this.showDateString)
        .then(response => {
          this.showtimesDate = response.data;
          this.timeString = response.data.time.time;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    disShowtime() {
      http
        .get("/showDatetime/showtimeid=" + this.booking.showId)
        .then(response => {
          this.showtime = response.data.showDate;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getZones() {
      http
        .get("/zone/" + this.booking.zoneId)
        .then(response => {
          if (response.data != null) this.zones = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
      this.disShowtime();
    },

    getSeats() {
      http
        .get("/seat/zoneid=" + this.booking.zoneId)
        .then(response => {
          if (response.data != null) {
            this.seats = response.data;
          }
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    showZone() {
      if (this.validtime) {
        if (
          this.booking.showId != null &&
          this.booking.showtimeId != null &&
          this.booking.userId != null
        ) {
          this.getZoneCheck = true;
          this.getZones();
        }
      } else {
        this.$fire({
          title: "กรุณากรอกข้อมูลให้ครบถ้วน!",
          text: "กรุณาเลือกรอบเวลาการแสดงก่อนทำรายการต่อไป",
          type: "error"
        }).then(r => {
          console.log(r.value);
        });
      }
    },

    showShowtime() {
      if (this.validshow) {
        if (this.booking.showId != null && this.booking.userId != null) {
          this.getShowtimeCheck = true;
          this.getShowtimes();
        }
      } else {
        this.$fire({
          title: "กรุณากรอกข้อมูลให้ครบถ้วน!",
          text: "กรุณาเลือกการแสดงก่อนทำรายการต่อไป",
          type: "error"
        }).then(r => {
          console.log(r.value);
        });
      }
    },

    showSeat() {
      if (this.validzone) {
        if (
          this.booking.showId != null &&
          this.booking.zoneId != null &&
          this.booking.showtimeId != null &&
          this.booking.timeId != null &&
          this.booking.userId != null
        ) {
          this.getSeats();
          this.getSeatCheck = true;
        }
      } else {
        this.$fire({
          title: "กรุณากรอกข้อมูลให้ครบถ้วน!",
          text: "กรุณาเลือกโซนก่อนทำรายการต่อไป",
          type: "error"
        }).then(r => {
          console.log(r.value);
        });
      }
    },

    checkSeat() {
      http
        .get(
          "/booking/showtime=" +
            this.booking.showtimeId +
            "/seat=" +
            this.booking.seatId
        )
        .then(response => {
          console.log(response);
          if (response.data[0] != null) {
            this.$fire({
              title: "เกิดข้อผิดพลาดขึ้น",
              text: "ที่นั่งถูกทำการจองไปก่อนหน้านี้แล้ว กรุณาเลือกที่นั่งใหม่",
              type: "error"
            }).then(r => {
              console.log(r.value);
            window.location.reload();
            });
          } else {
            this.saveBooking();
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },

    getInfo() {
      http
        .get("/seat/" + this.booking.seatId)
        .then(response => {
          if (
            response.data.seat_no == null ||
            response.data.seatInZone.zone == null ||
            response.data.seatInZone.price == null
          ) {
            this.$fire({
              title: "กรุณากรอกข้อมูลให้ครบถ้วน!",
              text: "กรุณาเลือกที่นั่งก่อนยืนยันการจองตั๋วการแสดง",
              type: "error"
            }).then(r => {
              console.log(r.value);
            });
          } else {
            this.seat = response.data.seat_no;
            this.zone = response.data.seatInZone.zone;
            this.price = response.data.seatInZone.price;
            console.log(response.data);
            this.getConfirm = true;
          }
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveBooking() {
      http
        .post(
          "/booking/success/" +
            this.booking.userId +
            "-" +
            this.booking.showtimeId +
            "-" +
            this.booking.seatId +
            "-" +
            this.booking.timeId,
          this.booking
        )
        .then(response => {
          console.log(response);
          this.$fire({
            title: "จองตั๋วสำเร็จ",
            text:
              this.showname +
              " รอบวัน " +
              this.showDateString +
              " รอบเวลา " +
              this.timeString +
              " โซน " +
              this.zone +
              " ที่นั่ง " +
              this.seat +
              " จำนวน 1 ที่นั่ง",
            type: "success"
          }).then(r => {
            console.log(r.value);
            window.location.reload();
          });
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },

    refreshList() {
      this.getSeats();
      this.getZones();
      this.getShowtimes();
      this.getShows();
    }
    /* eslint-enable no-console */
  },

  mounted() {
    this.getShows();
  }
};
</script>

