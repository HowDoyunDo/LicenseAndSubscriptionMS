<template>
  <div>
    <table class="table_board">
      <tr>
        <th>구독 정책 번호</th>
        <th>카테고리명</th>
        <th>이름</th>
        <th>가격</th>
        <th>등록일</th>
        <th>삭제</th>
      </tr>
      <tr v-for="(product, index) in productList" :key="product.id">
        <td>{{product.no}}</td>
        <td>{{product.category_no}}</td>
        <td>
          <span style="text-decoration-line: underline;">{{product.name}}</span>
        </td>
        <td>{{product.price | formatPrice}}</td>
        <td>{{product.reg_date | formatDate}}</td>
        <td>
          <a href="#" @click="prodctDelete(index)">삭제</a>
        </td>
      </tr>
    </table>

    <hr />    
      합계 :  원
   

  </div>
</template>

<script>
// import { productOneList } from "@/api/shr/product";
import moment from "moment";

export default {
  data() {
    return {
      productList: [],
    };
  },
  mounted() {
    this.productList = this.$store.getters.getProductList;
  },
  methods: {
    prodctDelete(index) {
      this.$store.dispatch("deleteOneProduct", index);
    }
  },
  filters: {
    formatDate: function(value) {
      return moment(value).format("YYYY/MM/DD HH:mm:ss");
    },
    formatPrice: function(value) {
      if (!value) return "";
      return value.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    }
  }
};
</script>

<style>
</style>