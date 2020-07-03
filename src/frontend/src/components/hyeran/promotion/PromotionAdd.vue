<template>
  <div>
    <form class="form" @submit.prevent="promotionAddSubmit">
      <table class="table_add">
        <tr>
          <th>프로모션명</th>
          <td>
            <input type="text" v-model="title" />
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
      <!-------productOneList Component 사용------>
      <div v-if="selected != ''">
        <table class="table_add">
          <tr>
            <th>선택된 제품</th>
          </tr>
        </table>
        <div class="select-product">
          <ProductOneList :discountPrice="discountPrice" :pageType="true"/>
        </div>
      </div>

      <br />
      <!------------->
      <table class="table_add">
        <tr>
          <th>할인율</th>
          <td>
            <input
              type="number"
              min="0"
              max="100"
              v-model.number="discount"
              @keyup="discountPriceForm"
              @blur="discountPriceForm"
            /> %
            <!--v-model.number="maxCount" -->
          </td>
        </tr>
        <tr>
          <th>사용 기간</th>
          <td>
            <input type="date" v-model="start_date" /> &nbsp;
            <input type="time" v-model="start_date_time" />
            ~
            <input type="date" v-model="end_date" /> &nbsp;
            <input type="time" v-model="end_date_time" />
          </td>
        </tr>
        <tr>
          <th>진행 여부</th>
          <td>
            <input type="radio" name="promotion_type" value="true" v-model="type" /> 진행 함
            <input type="radio" name="promotion_type" value="false" v-model="type" /> 진행 안 함
          </td>
        </tr>
      </table>
      <br />

      <div class="submit_btn">
        <button type="submit">프로모션 등록</button>
      </div>
    </form>
  </div>
</template>

<script>
import { productAddMixin } from "../mixins/productAdd";
import { promotionMixin } from "../mixins/promotionAdd";
import { promotionAdd } from "@/api/shr/promotion";

export default {
  mixins: [productAddMixin, promotionMixin],
  data() {
    return {
      type: "true",
      discountPrice: ""
    };
  },
  methods: {
    // 전송, 프로모션 등록
    async promotionAddSubmit() {
      // 폼 유효성 검사
      if (!this.promotionFormValidation()) {
        // 등록할 데이터
        const promotionAddData = { 
          title: this.title,
          selectedList: this.$store.getters["productStore_shr/getProductList"],
          discount: this.discount,
          start_date: this.start_date,
          start_date_time: this.start_date_time,
          end_date: this.end_date,
          end_date_time: this.end_date_time,
          type: this.type
        };

        if (promotionAddData.selectedList.length == 0) {
          alert("제품을 선택해주세요");
        } else {
          // 프로모션 submit 등록
          const { data } = await promotionAdd(promotionAddData);
          if (data == 1) {
            alert("등록 완료");
            this.$router.push("/promotion");
          } else {
            alert("등록 실패");
          }
        }
      }
    },

  },
  destroyed() {
    // 선택제품된 제품목록 초기화
    this.$store.commit("productStore_shr/productAllDelete");
  }
};
</script>

<style>
button {
  width: auto;
  padding: 0 15px;
  margin: 10px;
}
input[type="date"],
input[type="time"],
input[type="radio"] {
  text-align: center;
  border-style: none;
  font-size: 100%;
  border: 1px solid #dae1e7;
  -webkit-box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  padding: 0.5rem 0.75rem;
  margin: 5px;
}
</style>