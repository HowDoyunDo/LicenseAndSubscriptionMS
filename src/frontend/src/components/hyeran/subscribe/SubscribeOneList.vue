<template>
  <div>
    <table class="table_board">
      <tr>
        <th style="width: 7%;">번호</th>
        <th style="width: 17%;">구독정책명</th>
        <th style="width: 10%;">공개 여부</th>
        <th style="width: 10%;">이용 기준</th>
        <th style="width: 10%;">수량</th>
        <th style="width: 13%;">가격</th>
        <th style="width: 15%;">URL</th>
        <th v-if="promotionPolicy == undefined" style="width:7%">삭제</th>
      </tr>
      <tr v-for="(product, index) in policyList" :key="product.id">
        <td>{{product.no}}</td>
        <td>{{product.policy_title}}</td>
        <td>
          <span>{{product.visible ?'공개' : '비공개'}}</span>
        </td>
        <td>{{product.standard=='U'?'사용자' :'에이전트'}}</td>
        <td>{{product.max_count}}</td>
        <td style="letter-spacing: 0.5px; word-spacing: 5px;">
          <!--discountPrice : 할인가, 원가 style 차이 -->
          <span v-if="discountPrice" class="discountStyle">{{product.price | formatPrice}}</span>
          <span v-else>{{product.price | formatPrice}}</span>
          <!-- 할인가격 출력 -->
          <span
            v-if="discountPrice"
            style="color : red; padding : 5px"
          >({{product.price - (product.price*discountPrice) | formatPrice}})</span>
        </td>
         <td>
        {{product.url}}
        </td>
        <td v-if="promotionPolicy == undefined ">
          <a class="minus" @click="policyDelete(index)">
            <img src="@/assets/images/minus.png" class="minusImage" />
          </a>
        </td>
      </tr>
    </table>
    <!-- <div v-if="promotionPolicy != undefined" class="product-sum"></div> -->
    <div v-if="promotionPolicy != undefined " class="product-sum" style="color:black">
      <div v-if="pageType=='license'"></div>
      <div v-else>
        <hr />정책 가격 :
        <span class="discountStyle">{{promotionPolicy[0].price | formatPrice }}</span>
        <span
          style="color : red; padding : 5px"
        >{{promotionPolicy[0].price - (promotionPolicy[0].price*discountPrice) | formatPrice}} 원</span>
      </div>
    </div>
    <!--  프로모션 등록 시 합 안보이게-->
    <div v-else-if="pageType==true"></div>
    <div v-else class="product-sum">
      <hr />
      정책 합계 : {{policysSum | formatPrice}} 원
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      policyList: [],
      productSum: "",
      policySum: "",
      showModal: false
    };
  },
  props: ["promotionPolicy", "discountPrice", "pageType"],
  computed: {
    policysSum() {
      return this.$store.getters["subscribeStore/getPolicySum"];
    }
  },
  mounted() {
    this.policyList = this.$store.getters["subscribeStore/getPolicyList"];
  },
  beforeUpdate() {
    if (this.promotionPolicy != undefined) {
      // 프로모션 해당 제품(1개) 출력할 때
      this.policyList = this.promotionPolicy;
    } else {
      // 구독 정책 해당 제품 list 출력할 때
      this.policyList = this.$store.getters["subscribeStore/getPolicyList"];
    }
  },
  methods: {
    policyDelete(index) {
      this.$store.dispatch("subscribeStore/deleteOnePolicy", index);
    },
  }
};
</script>

<style scoped>
.product-sum {
  text-align: right;
  color: red;
  font-style: italic;
  font-size: 20px;
}
.minusImage {
  width: 22px;
  height: 22px;
}
.discountStyle {
  text-decoration-line: line-through;
}
input {
   outline:none;
}
</style>