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
        <th style="width:12%">삭제</th>
      </tr>

      <tr v-for="(product, index) in productList" :key="product.id">
        <td>{{product.no}}</td>
        <td>{{product.category_title}}</td>
        <td>
          <span style="text-decoration-line: underline;">{{product.name}}</span>
        </td>
        <td>{{product.price | formatPrice}}</td>
        <td>{{product.reg_date | formatDate}}</td>
        <td>
          <a href="#" @click="openInfo(product.no)">상세보기</a>
          <modal v-if="showModal" @closeee="showModal=false"  />
        </td>
        <td>
          <a class="minus" @click="prodctDelete(index)">
            <img src="@/assets/images/minus.png" class="minusImage" />
          </a>
        </td>
      </tr>
    </table>
    <div v-if="sumToogle=='promotioProduct'" class="product-sum"></div>
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
  props: ['sumToogle'],
  components: {
    modal: ProductModal
  },
  computed: {
    productsSum() {
      return this.$store.getters["subscribeStore/getProductSum"];
    }
  },
  mounted() {
    this.productList = this.$store.getters["subscribeStore/getProductList"];
  },
  methods: {
    prodctDelete(index) {
      this.$store.dispatch("subscribeStore/deleteOneProduct", index);
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
</style>