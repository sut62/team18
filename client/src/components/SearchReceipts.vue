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
                id="emp_name"
                label="Receipts ID"
                solo
                v-model="receipts.receiptsId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>

            <p v-if="receiptsCheck != ''">
              Receipts ID : {{receptsId}}
              Receipts Date : {{receiptsDate}}
            </p>

            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findReceipts" depressed large color="primary">Search</v-btn>
              </div>
            </v-col>
          </v-row>
          <v-card class="mx-auto" max-width="344">
              <v-card-text>
                <div>Word of the Day</div>
                <p class="display-1 text--primary">be•nev•o•lent</p>
                <p>adjective</p>
                <div class="text--primary">
                  well meaning and kindly.
                  <br />"a benevolent smile"
                </div>
              </v-card-text>
              <v-card-actions>
                <v-btn text color="deep-purple accent-4">Learn More</v-btn>
              </v-card-actions>
            </v-card>
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
        receiptsId: ""
      },
      receiptsCheck: false
    };
  },
  methods: {
    // function ค้นหาใบเสร็จด้วย ID
    findReceipts() {
      http
        .get("/receipts/" + this.receipts.receiptsId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.receiptsDate = response.data.receipts_datetime;
            this.receiptsCheck = response.status;
          } else {
            this.clear();
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    }
  }
};
</script>
