<template>
  <div>
    <div>
      <h2 v-if="modifyToggle==true || modifyAllToggle==true">구독 정책 수정</h2>
      <h2 v-else>라이선스 상세보기</h2>
      <br />
      <form class="form">
        <table class="table_add">
          <tr>
            <th>라이선스 번호</th>
            <td>{{licenseList.no}}</td>
          </tr>
          <tr>
            <th>라이선스 키</th>
            <td>{{licenseList.license_key}}</td>
          </tr>
          <tr>
            <th>라이선스 상태</th>
            <td>
              <div v-if="licenseList.activation=='A'">활성화</div>
              <div v-if="licenseList.activation=='F'">수량 가득참</div>
              <div v-if="licenseList.activation=='E'">기간종료</div>
            </td>
          </tr>
          <tr>
            <th>구독 정책명</th>
            <td>{{licenseList.policy_title}}</td>
          </tr>
          <tr>
            <th>이용 기준</th>
            <td>{{licenseList.standard=='A' ? '에이전트': '사용자'}}</td>
          </tr>
          <tr>
            <th>현재 사용량 / 최대 사용 수량</th>
            <td>{{licenseList.current_count}} / {{licenseList.max_count}}</td>
          </tr>
          <tr>
            <th>사용 기간</th>
            <td>{{licenseList.start_date | formatDate}} ~ {{licenseList.end_date | formatDate}}</td>
          </tr>

          <tr>
            <th>결제 금액</th>
            <td>{{licenseList.total_price | formatPrice}}</td>
          </tr>
          <tr>
            <th>결제 현황</th>
            <td>{{licenseList.month_payment == true? '정기결제' : '일반결제'}}</td>
          </tr>
          <tr>
            <th>주문일</th>
            <td>{{licenseList.reg_date | formatDate}}</td>
          </tr>
        </table>
        <br />

        <table class="table_add">
          <tr>
            <th>포함 제품</th>
          </tr>
        </table>
        <div class="select-product">
          <ProductOneList :promotionProduct="productList" :pageType="'license'" />
        </div>

        <div class="submit_btn">
          <button type="button" @click="listClick">목록</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import ProductOneList from "@/components/hyeran/product/ProductOneList.vue";
import { subscribeMixin } from "../mixins/subscribeInfo";
import { licenseOneList } from "@/api/shr/license";

export default {
  mixins: [subscribeMixin],
  data() {
    return {
      license_no: "",
      policy_no: "",
      productList: "",
      licenseList: ""
    };
  },
  components: {
    ProductOneList
  },
  async created() {
    this.license_no = this.$route.params.license_no;
    this.policy_no = this.$route.params.policy_no;

    // 구독정책번호로 해당 정책 list setting 위한 스토어 저장
    await this.$store.dispatch(
      "subscribeStore/selectOneSubscribe",
      this.policy_no
    );
    const resList = await this.$store.getters[
      "subscribeStore/getSubscribeOneList"
    ];
    // 제품 리스트
    this.productList = resList.selectedList;

    // 라이선스 목록
    const { data } = await licenseOneList({ no: this.license_no });
    this.licenseList = data[0];
  },
  methods: {
    // 목록
    listClick() {
      this.$router.push({ name: "licneseList" });
    },
    //상세보기
    openInfo(productNo) {
      console.log("상세보기", productNo);
      // 상세보기 클릭 시 제품 설명 새창으로.
      this.showModal = true;
    }
  }
};
</script>


<style>
button {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}
</style>