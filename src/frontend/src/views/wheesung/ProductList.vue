<template>
  <div class="contents">
    <div>
      <h2>제품 목록</h2>
      <div style="text-align: right; margin: 10px;">
        <input class="cssbtn" type="button" value="등록" @click="movetoadd" />
      </div>
      <table class="table_board">
        <tr style="float:center;">
          <th style="width:100px;">번호</th>
          <th style="width:250px;text-align: left; padding: 0 0 0 20px;">제품명</th>
          <th style="text-align: left; width:200px;">제품 설명</th>
          <th style="width:100px;">가격</th>
          <th style="width:100px;">등록일</th>
          <th style="width:100px;"></th>
        </tr>
        <tr v-for="(product, idx) in list" :key="product.no">
          <td>{{ idx + 1 }}</td>
          <td style="text-align: left; padding: 0 0 0 20px;">{{ product.name }}</td>
          <td style="text-align: left;">{{ product.comments }}</td>
          <td>{{ product.price | formatPrice }}</td>
          <td>{{ product.reg_date }}</td>
          <td>
            <a @click="productChangeForm(product.no)" style="cursor:pointer;">수정</a> /
            <a @click="productDel(product.name)" style="cursor:pointer;">삭제</a>
          </td>
        </tr>
      </table>
      <!-- <Page
        :total-pages="3"
        :total="113"
        :per-page="10"
        :current-page="currentPage"
        @pagechanged="onPageChange"
      ></Page> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import Page from "../../components/wheesung/Page";

export default {
  // components: {
  //   Page
  // },
  data() {
    return {
      list: "",
      name: "",
      comments: "",
      price: 0,
      currentPage: 1
    };
  },
  methods: {
    onPageChange(page) {
      console.log(page);
      this.currentPage = page;
    },
    productDel(pname) {
      axios
        .post("/api/productdel", {
          name: pname
        })
        .then(result => {
          if (result.status == 200) {
            axios.get("/api/productlist").then(result => {
              this.list = result.data;
            });
          }
        });
    },
    productChangeForm(no) {
      this.$router.push({
        name: "productchangeform",
        params: { no: no }
      });
    },
    movetoadd() {
      this.$router.push("/productadd");
    }
  },
  created() {
    axios.get("/api/productlist").then(result => {
      this.list = result.data;
    });
  },
  filters: {
    formatPrice(value) {
      if (!value) return "";
      return value.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    }
  }
};
</script>

<style>
.cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 200px;
  height: 40px;
}

.table_board th {
  text-align: center;
  font-size: 15px;
  height: 40px;
}

.table_board td {
  height: 40px;
}
</style>