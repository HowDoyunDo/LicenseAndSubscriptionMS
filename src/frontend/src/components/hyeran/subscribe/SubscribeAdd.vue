<template>
  <div>
    <form class="form" @submit.prevent="subscribeAdd">
      <table class="table_add">
        <tr>
          <th>구독 정책명</th>
          <td>
            <input type="text" v-model="policy_title" />
          </td>
        </tr>
        <tr>
          <th>정책 공개 여부</th>
          <td>
            <input type="radio" name="public_radio" value="true" v-model="visible" /> 공개
            <input type="radio" name="public_radio" value="false" v-model="visible" /> 비공개
          </td>
        </tr>
        <tr>
          <th>이용 기준</th>
          <td>
            <input type="radio" name="standard" value="U" v-model="standard" /> 사용자
            <input type="radio" name="standard" value="A" v-model="standard" /> 에이전트
          </td>
        </tr>
        <tr>
          <th>수량</th>
          <td>
            <input type="number" min="0" v-model="max_count" /> 개
            <!--v-model.number="maxCount" -->
          </td>
        </tr>
        <tr>
          <th>가격</th>
          <td>
            <input
              type="text"
              min="0"
              v-model="format"
              @keyup="numberFormat"
              style="width : 150px; text-align:center;"
            /> 원
            <!-- @blur="formatPrice" -->
          </td>
        </tr>
        <tr>
          <th>제품 선택</th>
          <td>
            <select v-model="selected" class="select-box" @change="addProduct()">
              <option disabled value>제품을 선택해주세요</option>
              <option
                v-for="(list) in productList"
                :value="list.no"
                :key="list.no"
              >{{list.name}} - {{list.comments}}</option>
            </select>
          </td>
        </tr>
      </table>
      <br />

      <div v-if="selected != ''">
        <table class="table_add">
          <tr>
            <th>선택된 제품</th>
          </tr>
        </table>
        <div class="select-product">
          <ProductOneList />
        </div>
      </div>

      <div class="submit_btn">
        <button type="submit">구독 정책 등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import { subscribeAdd } from "@/api/shr/subscribe";
import { productAddMixin } from "../mixins/productAdd";
import { formatMixin } from "../mixins/numberFormat";
import { subscribeMixin } from "../mixins/subscribeInfo";

export default {
  mixins: [productAddMixin, formatMixin , subscribeMixin ],
  data() {
    return {
      visible: "true"
    };
  },
  destroyed () {
    // 선택제품된 제품목록 초기화
    this.$store.commit('productStore_shr/productAllDelete');
  },
  methods: {
    // 전송, 구독 정책 등록
    async subscribeAdd() {
      // 폼 유효성 검사
      if(!this.formValidation()){
        // 등록할 데이터
        const subscribeAddData = {
          policy_title: this.policy_title,
          visible: this.visible,
          standard: this.standard,
          max_count: this.max_count,
          price: this.uncomma(this.format),
          selectedList: this.$store.getters["productStore_shr/getProductList"]
        };

        if (subscribeAddData.selectedList.length == 0) {
          alert("제품을 선택해주세요");
        } else {
          // submit
          const { data } = await subscribeAdd(subscribeAddData);
      
          if (data == 1) {
            alert("등록 완료");
            this.$router.push("/subscribe/alllist");
          } else {
            alert("등록 실패");
          }
        }
      }
    }
  }
};
</script>

<style scoped>
button {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}
input {
   outline:none;
}
</style>