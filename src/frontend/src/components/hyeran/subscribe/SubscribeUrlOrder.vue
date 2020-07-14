<template>
  <div class="contents">
    <h2>구독 신청서 작성</h2>
    <p class="tip">서로 다른 정책의 제품들의 일괄 구매를 원하시는 고객님께서는 견적 문의 부탁드립니다.</p>
    <div class="info-border">
      <table class="info-table">
        <tr>
          <th>정책명</th>
          <td>{{policyList.policy_title}}</td>
        </tr>
        <tr>
          <th>이용 기준</th>
          <td>{{policyList.standard=='A' ? '에이전트' : '사용자'}}</td>
        </tr>
        <tr>
          <th>최대 사용 수</th>
          <td>{{policyList.max_count}}</td>
        </tr>
        <tr>
          <th>이용 개월 수</th>
          <td>
            <div v-if="visible=='t'">
              <input type="number" min="1" id="usemonth" v-model="usemonth" /> 개월
            </div>
            <div v-else>{{usemonth}} 개월</div>
            <div style="font-size:12px; color:red;">* 개월 수는 30일로 계산됩니다.</div>
          </td>
        </tr>
        <tr>
          <th>구독 시작일</th>
          <td>{{nowDate}}</td>
        </tr>
        <tr>
          <th>구독 종료일</th>
          <td>{{endDate}}</td>
        </tr>
         <tr v-if="promotion_type=='t'">
          <th>정책 적용 할인</th>
          <td>
            <template>
              <ul>
                <li
                  v-for="promotion in promotionList"
                  v-bind:key="promotion.no"
                  style="margin-bottom:10px"
                >
                  프로모션 명 : {{ promotion.title }}
                  <br />
                  할인률 : {{ promotion.discount }}%
                  <br />제품 정가(할인액) : <span> {{ promotion.product_price |formatPrice}}원</span>
                  <span style="color: red"> (- {{(promotion.discount/100 * promotion.product_price) | formatPrice}}원)</span>
                </li>
              </ul>
              <template v-if="promotionList==''">적용된 프로모션이 없습니다.</template>
            </template>
          </td>
        </tr>
        <tr v-if="promotion_type=='t'">
          <th>제품 적용 할인</th>
          <td>
            <template>
              <ul>
                <li
                  v-for="promotion in promotionList"
                  v-bind:key="promotion.no"
                  style="margin-bottom:10px"
                >
                  프로모션 명 : {{ promotion.title }}
                  <br />
                  할인률 : {{ promotion.discount }}%
                  <br />제품 정가(할인액) : <span> {{ promotion.product_price |formatPrice}}원</span>
                  <span style="color: red"> (- {{(promotion.discount/100 * promotion.product_price) | formatPrice}}원)</span>
                </li>
              </ul>
              <template v-if="promotionList==''">적용된 프로모션이 없습니다.</template>
            </template>
          </td>
        </tr>
      </table>
      <hr style="border-top: 1px solid black;" />
      <div
        id="price-title"
        style="width: 80%; text-align:right; font-size:17px; font-weight: bold; float:left"
      >
        <p style="color: gray">정가 :</p>
        <p style="color: red">할인 금액 :</p>
      </div>
      <div
        id="final-price"
        style="width: 20%; text-align:right; font-size:17px; font-weight: bold; float:left"
      >
        <p style="color: gray">{{ policyList.price | formatPrice }} 원</p>
        <p style="color: red">222 원</p>
      </div>
      <br />
      <br />
      <hr style="border-top: 1px solid gray;" />
      <div
        id="price-title"
        style="width: 80%; text-align:right; font-size:17px; font-weight: bold; float:left"
      >
        <p style="color: black">결제 금액 :</p>
      </div>
      <div
        id="final-price"
        style="width: 20%; text-align:right; font-size:17px; font-weight: bold; float:left"
      >1111 원</div>
      <br />
      <br />
    </div>
  </div>
</template>
<script>
import { subscribeOneList } from "@/api/shr/subscribe";
import { promotionCheckPNo } from "@/api/shr/promotion";
import moment from "moment";

export default {
  data() {
    return {
      policy_no: this.$route.params.policy_no,
      promotion_type: this.$route.params.promotion_type,
      visible: this.$route.params.visible,
      usemonth: this.$route.params.usemonth,
      policyList: "",
      start_date: "",
      promotionList: ""
    };
  },
  // 네비게이션 라우터가드
  beforeRouteEnter(to, from, next) {
    var regexp = /[^0-9]/gi;
    //usermonth: 공개정책(t))이면 수정가능, 비공개정책(f)은 수정 불가

    // 정책 번호 check, subscribeOneList : axios
    subscribeOneList({ subscribeNo: to.params.policy_no })
      .then(() => {
        console.log(to.params.promotion_type);
        if (
          to.params.usemonth <= 0 ||
          regexp.exec(to.params.usemonth) !== null
        ) {
          alert(" 잘못된 접근입니다.\n 이용 개월 수가 올바르지 않습니다.");
        } else if (
          to.params.promotion_type != "t" &&
          to.params.promotion_type != "f"
        ) {
          alert(" 잘못된 접근입니다.\n 프로모션이 올바르지 않습니다.");
        } else if (to.params.visible != "t" && to.params.visible != "f") {
          alert(" 잘못된 접근입니다.\n 공개/비공개가 올바르지 않습니다.");
        } else {
          next();
        }
      })
      .catch(function(error) {
        if (error) {
          alert(" 잘못된 접근입니다.\n 정책번호가 올바르지 않습니다.");
        }
      });
  },
  async created() {
    // 정책번호로 가져오기 정책들
    const { data } = await subscribeOneList({ subscribeNo: this.policy_no });
    this.policyList = data[0];

    // 해당 제품 번호가 진행하는 프로모션 체크
    const promotionCheck = [];
    for (let i = 0; i < this.policyList.selectedList.length; i++) {
      promotionCheck[i] = this.policyList.selectedList[i].no;
    }
    console.log(promotionCheck);
    // 제품 여러개ㅇㅇㅇ in?
    const res = await promotionCheckPNo({ promotionProduct: promotionCheck });
    this.promotionList = res.data;
  },
  computed: {
    nowDate() {
      // 구독 시작일 현재일로 고정
      return moment().format("YYYY-MM-DD");
    },
    endDate() {
      const enddatee = moment(this.nowDate)
        .add(this.usemonth * 30, "d")
        .format("YYYY-MM-DD");
      return enddatee;
    }
  },
  methods: {}
};
</script>

<style>
.tip {
  border: 2px solid gray;
  color: red;
  font-size: 15px;
  padding: 20px;
  border-radius: 5px;
}
.info-border {
  border: 2px solid lightgray;
  border-radius: 5px;
  padding: 10px;
}
.info-table {
  border-top: 2px solid #ccc;
  border-bottom: 2px solid #ccc;
  width: 100%;
  max-width: 100%;
  border-spacing: 0;
  border-collapse: collapse;
  table-layout: fixed;
}
.info-table tr {
  border-bottom: 1px solid #ccc;
  font-size: 15px;
}
.info-table th {
  background: #eaeaea;
  font-weight: bold;
  border: none;
  width: 20%;
  text-align: center;
  padding: 15px;
}
.info-table td {
  padding: 15px;
  font-weight: bold;
}
.info-table input {
  width: 100px;
}
.selPayM button {
  width: 20%;
  height: 80px;
  margin: 0 20px 0;
  background: gray;
  font-size: 20px;
}
.nav-tabs li.active a {
  background-color: #d9edf7;
}
input {
   outline:none;
}
</style>