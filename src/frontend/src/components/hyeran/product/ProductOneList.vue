<template>
  <div>
    <table class="table_board">
      <tr>
        <th>제품번호</th>
        <th>카테고리명</th>
        <th>제품명</th>
        <th>가격</th>
        <th>등록일</th>
        <th>상세보기</th>
        <th v-if="promotionProduct == undefined" style="width:12%">삭제</th>
      </tr>
      <tr v-for="(product, index) in productList" :key="product.id">
        <td>{{product.no}}</td>
        <td>{{product.category_title}}</td>
        <td>
          <span style="text-decoration-line: underline;">{{product.name}}</span>
        </td>
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
        <td>{{product.reg_date | formatDate}}</td>
        <td>
          <a href="#" @click="openInfo(product.no)">상세보기</a>
          <modal v-if="showModal" @closeee="showModal=false" />
        </td>
        <td v-if="promotionProduct == undefined ">
          <a class="minus" @click="prodctDelete(index)">
            <img src="@/assets/images/minus.png" class="minusImage" />
          </a>
        </td>
      </tr>
    </table>
    <!-- <div v-if="promotionProduct != undefined" class="product-sum"></div> -->
    <div v-if="promotionProduct != undefined " class="product-sum" style="color:black">
      <div v-if="pageType=='license'"></div>
      <div v-else>
        <hr />제품 가격 :
        <span class="discountStyle">{{promotionProduct[0].price | formatPrice }}</span>
        <span
          style="color : red; padding : 5px"
        >{{promotionProduct[0].price - (promotionProduct[0].price*discountPrice) | formatPrice}} 원</span>
      </div>
    </div>
    <!--  프로모션 등록 시 합 안보이게-->
    <div v-else-if="pageType==true"></div>
    <div v-else class="product-sum">
      <hr />
      제품 합계 : {{productsSum | formatPrice}} 원
    </div>
  </div>
</template>

<script>
import ProductModal from "@/components/hyeran/product/ProductModal.vue";

export default {
  data() {
    return {
      productList: [],
      productSum: "",
      showModal: false
    };
  },
  props: ["promotionProduct", "discountPrice", "pageType"],
  components: {
    modal: ProductModal
  },
  computed: {
    productsSum() {
      return this.$store.getters["productStore_shr/getProductSum"];
    }
  },
  mounted() {
    this.productList = this.$store.getters["productStore_shr/getProductList"];
  },
  beforeUpdate() {
    if (this.promotionProduct != undefined) {
      // 프로모션 해당 제품(1개) 출력할 때
      this.productList = this.promotionProduct;
    } else {
      // 구독 정책 해당 제품 list 출력할 때
      this.productList = this.$store.getters["productStore_shr/getProductList"];
    }
  },
  methods: {
    prodctDelete(index) {
      this.$store.dispatch("productStore_shr/deleteOneProduct", index);
    },
    openInfo(productNo) {
      console.log("상세보기", productNo);
      // 상세보기 클릭 시 제품 설명 새창으로.
      this.showModal = true;
    }
  }
};
</script>

<style>
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
</style>