<template>
  <v-content>
    <v-card-text
      headline
      align="center"
      class="display-3 font--weight-bold blue darken-3 yellow--text text--lighten-1"
    >SEARCH RECEIPTS</v-card-text>

    <v-row>
      <v-col cols="18">
        <v-form>
          <v-row justify="center" style="height: 70px;">
            <!-- receipts id -->
            <v-col cols="3">
              <v-text-field
                id="re_id"
                label="Receipts ID"
                solo
                v-model="searchId"
                :rules="[(v) => !!v || 'กรุณาใส่ Receipts ID']"
                required
              ></v-text-field>
            </v-col>

            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findReceipts" depressed large color="light-green accent-4">Search</v-btn>
              </div>
            </v-col>
          </v-row>
          <br>
          <v-data-table :headers="headers" 
                        :items="items" 
                        :items-per-page="5" 
                        class="elevation-1">
          </v-data-table>
        </v-form>
      </v-col>
    </v-row>
  </v-content>
</template>

<script>
import http from "../http-common";

export default {
  name: "searchReceipts",
  data() {
    return {
        headers: [
        { text: "ลำดับที่",sortable: false, value: "id" },
        { text: "ชื่อคุณลูกค้า",sortable: false, value: "booking.chooseUser.name" },
        { text: "ชื่อการแสดง",sortable: false, value: "booking.chooseShowtime.show.title" },
        { text: "สถานที่",sortable: false, value: "booking.chooseShowtime.location.location" },
        { text: "วันที่ทำการแสดง",sortable: false, value: "booking.chooseShowtime.showDate" },
        { text: "เวลาทำการแสดง",sortable: false, value: "booking.chooseShowtime.time.time" },
        { text: "โซนและเลขที่นั่ง",sortable: false, value: "booking.chooseSeat.seat_no" },
        { text: "พนักงานผู้ออกใบเสร็จ",sortable: false, value: "employee.name" },
      ],
      items: [],
      searchId: undefined,
    };
  },
  methods: {
    // function ค้นหาใบเสร็จด้วย ID
    findReceipts() {
      http
            .get("/receipts/searchId=" + this.searchId)
            .then(response  => {
                this.items = response.data;
                console.log(JSON.parse(JSON.stringify(response.data)));
                if(this.items.length == 0) {
                    this.$fire({
                    title: "ไม่พบ Receipts ID นี้",
                    type: "error"
                    })
                    .then(r => {
                    console.log(r.value);
                    window.location.reload();
                    });
                }
            })
          .catch(e => {
            console.log("Error in findReceipts() :" + e);
        });
    },
  }
};
</script>
