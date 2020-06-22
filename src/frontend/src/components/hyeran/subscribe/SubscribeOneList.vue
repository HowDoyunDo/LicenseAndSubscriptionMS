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
      <tr>
        <td>1</td>
        <td>1</td>
        <td>
          <span style="text-decoration-line: underline;">iWorks</span>
        </td>
        <td>280,000</td>
        <td>2020-06-19</td>
        <td>
          <a href="#" @click="prodct">삭제 {{productno}} ss</a>
        </td>
      </tr>
      <tr>
        <td colspan="6">실제 데이터</td>
      </tr>
      <tr v-for="product in productList" :key="product.no">
        <td>{{product.no}}</td>        
        <td>{{product.category_no}}</td>        
        <td>{{product.name}}</td>        
        <td>{{product.price}}</td>        
        <td>{{product.reg_date}}</td>        
        <td>삭제</td>        
      </tr>
    </table>
  </div>
</template>

<script>
// import { productOneList } from "@/api/shr/subscribe";

// export default {
//   props: ["productno"],
//   data() {
//     return {
//       productList: "",
//     };
//   },
//   methods: {
//     async prodct() {
//       console.log(JSON.stringify({ "no" :  this.productno}))
//       const {data} = await productOneList({ "no" : this.productno });
//       this.productList = data;
//     }
//   }
// };
</script>

<style>
</style>