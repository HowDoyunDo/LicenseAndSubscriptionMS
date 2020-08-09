<template>
  <div id="product" class="contents">
    <h1>제품 조회</h1>
    <Detail>
      <!-- slot 위치 -->
      <div id="inner">
        <ul class="user" style="float:left">
          <h4 style="color:gray">{{ product.title }} _사용자 정책</h4>
          <li v-for="(upolicy, i) in uPolicies" v-bind:key="upolicy.no">
            <div class="panel panel-info">
              <div class="panel-heading">{{ product.title }}<span v-if="upolicy.price === 0"> _체험판</span></div>
              <div class="panel-body">
                <table align="center">
                  <tr>
                    <th>최대 사용 수</th>
                    <th>사용료/월</th>
                  </tr>
                  <tr>
                    <td v-if="upolicy.max_count === 0">제한없음</td>
                    <td v-else>{{ upolicy.max_count }}명</td>
                    <td>{{ numberWithCommas(upolicy.price) }}원</td>
                  </tr>
                </table>

                <br />

                <label>포함제품</label>
                <div id="includes" v-for="tab in tabs" :key="tab.no">
                  {{ tab.name }}
                </div>
                <br />

                <template v-if="upolicy.price === 0">
                  <button
                    style="width:110px; margin-bottom:10px;"
                    @click="getPromotionAndOrder(i, upolicy.standard, 0)"
                  >
                    체험판 구독
                  </button><br>
                  <button style="visibility: hidden">asd</button><br>
                  <button style="visibility: hidden">asd</button>
                </template>
                <template v-else>
                  <button
                    style="width:110px; margin-bottom:10px"
                    @click="getPromotionAndOrder(i, upolicy.standard, 0)"
                  >
                    일반 구독
                  </button>
                  <button
                    style="width:110px; margin-bottom:10px; background-color:#0078d4"
                    @click="getPromotionAndOrder(i, upolicy.standard, 1)"
                  >
                    1년 구독
                  </button>
                  <button
                    style="width:110px; margin-bottom:10px; background-color:#345497"
                    @click="getPromotionAndOrder(i, upolicy.standard, 2)"
                  >
                    2년 구독
                  </button>
                </template>
              </div>
            </div>
          </li>
          <li v-if="uPolicies.length === 0" style="text-align: center">
            정책 없음
          </li>
        </ul>
      </div>

      <br />

      <div id="inner">
        <ul class="agent" style="float:left">
          <h4 style="color:gray">{{ product.title }} _에이전트 정책</h4>
          <li v-for="(apolicy, i) in aPolicies" v-bind:key="apolicy.no">
            <div class="panel panel-info">
              <div class="panel-heading">{{ product.title }}<span v-if="apolicy.price === 0"> _체험판</span></div>
              <div class="panel-body">
                <table align="center">
                  <tr>
                    <th>최대 사용 수</th>
                    <th>사용료/월</th>
                  </tr>
                  <tr>
                    <td v-if="apolicy.max_count === 0">제한없음</td>
                    <td v-else>{{ apolicy.max_count }}</td>
                    <td>{{ numberWithCommas(apolicy.price) }}원</td>
                  </tr>
                </table>

                <br />

                <label>포함제품</label>
                <div id="includes" v-for="tab in tabs" :key="tab.no">
                  {{ tab.name }}
                </div>
                <br />

                <template v-if="apolicy.price === 0">
                  <button
                    style="width:110px; margin-bottom:10px"
                    @click="getPromotionAndOrder(i, apolicy.standard, 0)"
                  >
                    체험판 구독
                  </button><br>
                  <button style="visibility: hidden">asd</button><br>
                  <button style="visibility: hidden">asd</button>
                </template>
                <template v-else>
                  <button
                    style="width:110px"
                    @click="getPromotionAndOrder(i, apolicy.standard, 0)"
                  >
                    일반 구독
                  </button>
                  <button
                    style="width:110px; margin-bottom:10px; background-color:#0078d4"
                    @click="getPromotionAndOrder(i, apolicy.standard, 1)"
                  >
                    1년 구독
                  </button>
                  <button
                    style="width:110px; margin-bottom:10px; background-color:#345497"
                    @click="getPromotionAndOrder(i, apolicy.standard, 2)"
                  >
                    2년 구독
                  </button>
                </template>
              </div>
            </div>
          </li>
          <li v-if="aPolicies.length === 0" style="text-align: center">
            정책 없음
          </li>
        </ul>
      </div>
    </Detail>

    <button id="subBtn" @click="subBtn()">
      바로 구독 신청
    </button>

    <button id="upBtn" @click="upBtn()">
      <img
        src="@/assets/images/moveup.png"
        style="width:30px; height:30px; display: block"
      />
    </button>
  </div>
</template>

<script>
import Detail from "@/components/doyun/user_admin/subscribe/Detail";
import axios from "axios";
export default {
  methods: {
    async getPromotionAndOrder(idx, type, per) {
      if (this.userInfo.co_number !== undefined) {
        // 고객관리자 로그인이라면
        // 프로모션 정보를 가져와 store에 저장
        this.$store.commit("productStore/SET_PROMOTION", "");

        if (type === "U") {
          await axios
            .get("/api/api/promotionPolicyCheckPNo", {
              params: {
                policyNo: this.uPolicies[idx].no,
              },
            })
            .then((res) => {
              this.$store.commit("productStore/SET_PROMOTION", res.data);
              console.log(res.data);
            });
        } else if (type === "A") {
          console.log(this.aPolicies[idx].no);
          await axios
            .get("/api/api/promotionPolicyCheckPNo", {
              params: {
                policyNo: this.aPolicies[idx].no,
              },
            })
            .then((res) => {
              this.$store.commit("productStore/SET_PROMOTION", res.data);
              console.log(res.data);
            });
        }

        this.$router.push({
          name: "SubscribeOrder",
          params: { policyidx: idx, policytype: type, per: per },
        });

      } else if (this.userInfo.name === undefined) {
        // 비로그인 상태라면
        alert("구독 신청을 위해 로그인을 해주세요.");
        this.$router.push("/login");
      }
    },
    date_to_str(format) {
      var year = format.getFullYear();
      var month = format.getMonth() + 1;
      if (month < 10) month = "0" + month;
      var date = format.getDate();
      if (date < 10) date = "0" + date;
      var hour = format.getHours();
      if (hour < 10) hour = "0" + hour;
      var min = format.getMinutes();
      if (min < 10) min = "0" + min;
      var sec = format.getSeconds();
      if (sec < 10) sec = "0" + sec;
      return (
        year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec
      );
    },
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    subBtn() {
      document.documentElement.scrollTop = document.querySelector('#subhead').offsetTop + 90;
    },
    upBtn() {
      document.documentElement.scrollTop = 0;
    },
  },
  components: {
    Detail,
  },
  computed: {
    product: function() {
      return this.$store.state.productStore.product;
    },
    tabs: function() {
      return this.$store.state.productStore.tabs;
    },
    uPolicies: function() {
      return this.$store.state.productStore.uPolicies;
    },
    aPolicies: function() {
      return this.$store.state.productStore.aPolicies;
    },
    userInfo: function() {
      return this.$store.state.userinfo.userInfo;
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
#product ul {
  list-style: none;
  width: 100%;
  padding: 10px;
}
.user li,
.agent li {
  padding: 10px;
  float: left;
}
.user .panel-body,
.agent .panel-body {
  text-align: center;
}
.panel {
  width: 230px;
  margin: 0;
}
.user table ul,
.agent table ul {
  border-top: 2px solid #ccc;
  border-bottom: 2px solid #ccc;
  width: 100%;
  max-width: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  table-layout: fixed;
  text-align: center;
}
.user table th,
.agent table th {
  background: #eaeaea;
  font-weight: normal;
  border: none;
  text-align: center;
  padding: 0 5px 0 5px;
}
.user table tr,
.agent table tr {
  border-bottom: 1px solid #ccc;
}
.user table,
.agent table {
  width: 180px;
}
#includes {
  color: blueviolet;
}
#subBtn {
  font-size: 20px;
  width: 150px;
  height: 50px;
  position: fixed;
  right: 50px;
  bottom: 50px;
  z-index: 1000;
  opacity: 0.8;
}
#upBtn {
  font-size: 20px;
  width: 42px;
  height: 40px;
  position: fixed;
  right: 50px;
  bottom: 110px;
  z-index: 1000;
  background-color: #bbb;
  opacity: 0.7;
}
#inner {
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
}
</style>