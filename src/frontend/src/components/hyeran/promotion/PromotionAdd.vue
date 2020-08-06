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
          <th>기준</th>
          <td style="padding-left:15px">
            <!-- <input
              type="radio"
              name="promotionselect"
              value="policy"
              v-model="promotionselect"
              @change="radioChange()"
            /> 정책
            <input
              type="radio"
              name="promotionselect"
              value="product"
              v-model="promotionselect"
              @change="radioChange()"
            /> 제품 -->
            정책
          </td>
        </tr>

        <!-- <tr v-if="promotionselect=='product'">
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
        </tr> -->
        <tr v-if="promotionselect=='policy'">
          <th>정책 선택</th>
          <td>
            <select v-model="selectedPolicy" class="select-box" @change="addPolicy()">
              <option disabled value>정책을 선택해주세요</option>
              <option
                v-for="(list) in policyList"
                :value="list.no"
                :key="list.no"
              >{{list.policy_title}}</option>
            </select>
          </td>
        </tr>
      </table>

      <br />
      <!-------productOneList Component 사용------>
      <!-- <div v-if="promotionselect=='product' && selected != ''">
        <table class="table_add">
          <tr>
            <th>선택된 제품</th>
          </tr>
        </table>
        <div class="select-product">
          <ProductOneList :discountPrice="discountPrice" :pageType="true" />
        </div>
      </div> -->

      <!-- <br /> -->
      <!------------->
      <!-------SubscribeOneList Component 사용------>
      <!-- <div v-if="promotionselect=='policy' && selectedPolicy != ''"> -->
      <br>
      <div>
        <table class="table_add">
          <tr>
            <th>선택된 정책</th>
          </tr>
        </table>
        <div class="select-product" style="min-height:123px">
          <SubscribeOneList :discountPrice="discountPrice" :pageType="true" />
        </div>
      </div>
      <br>

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
import { /*promotionAdd ,*/ promotionPolicyAdd} from "@/api/shr/promotion";

export default {
  mixins: [productAddMixin, promotionMixin],
  data() {
    return {
      type: "true",
      discountPrice: "",
      promotionselect: "policy"
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
          selectedPolicyList : this.$store.getters["subscribeStore/getPolicyList"],
          discount: this.discount,
          start_date: this.start_date,
          start_date_time: this.start_date_time,
          end_date: this.end_date,
          end_date_time: this.end_date_time,
          type: this.type
        };

        // if (this.promotionselect=='product' && promotionAddData.selectedList.length == 0) {
        //   alert("제품을 선택해주세요");
        // } else 
        if(this.promotionselect=='policy' &&  promotionAddData.selectedPolicyList.length == 0) {
          alert(" 정책을 선택해주세요");
        } else {
         
          // 제품 프로모션 submit 등록
          // if(this.promotionselect =='product'){
          //  const {data} = await promotionAdd(promotionAddData);

          //   if (data == 1) {
          //     alert("등록 완료");
          //     this.$router.push("/promotion");
          //   } else {
          //     alert("등록 실패");
          //   }
          // }  
          // 정책 프로모션 submit 등록  
          // else 
          if(this.promotionselect=='policy'){ 
            const res = await promotionPolicyAdd(promotionAddData);
            if (res.data == 1) {
              alert("등록 완료");
              this.$router.push("/promotion/policy");
            } else {
              alert("등록 실패");
            }
          }

          

        }
      }
    },
    // radioChange() {
    //   this.selectedPolicy='';
    //   this.selected='';
    //   this.$store.commit("productStore_shr/productAllDelete");
    //   this.$store.commit("subscribeStore/policyAllDelete");
    // }
  },
  destroyed() {
    // 선택제품된 제품목록 초기화
    this.$store.commit("productStore_shr/productAllDelete");
    this.$store.commit("subscribeStore/policyAllDelete");
  }
};
</script>

<style scoped>
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
   outline:none;
}
</style>