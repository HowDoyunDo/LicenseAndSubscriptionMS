<template>
  <div class="contents">
    <div>
      <h1>제품 목록</h1>
      <div style="margin: 0 0 10px 0;">
        <input
          style="margin: 8px 0 0 0;"
          id="myInput"
          type="text"
          v-on:input="keyword = $event.target.value"
          placeholder="제품명 입력"
        />
        <input style="float: right;" class="cssbtn" type="button" value="등록" @click="movetoadd" />
      </div>
      <table class="table_board">
        <tr style="float:center;">
          <th style="width:5%;">번호</th>
          <th style="width:30%;">제품명</th>
          <th style="width:30%;">제품 설명</th>
          <th style="width:10%">가격</th>
          <th style="width:15%">등록일</th>
          <th style="width:15%;"></th>
        </tr>
        <tr v-for="(product, idx) in filteredList" :key="product.no">
          <td>{{ idx + 1 }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.comments }}</td>
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
      ></Page>-->
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
      currentPage: 1,
      keyword: "",
    };
  },
  computed: {
    filteredList() {
      return Object.values(this.list).filter((post) => {
        return post.name.toLowerCase().includes(this.keyword.toLowerCase());
      });
    },
  },
  methods: {
    onPageChange(page) {
      console.log(page);
      this.currentPage = page;
    },
    productDel(pname) {
      axios
        .post("/api/productdel", {
          name: pname,
        })
        .then((result) => {
          if (result.status == 200) {
            axios.get("/api/productlist").then((result) => {
              this.list = result.data;
            });
          }
        });
    },
    productChangeForm(no) {
      this.$router.push({
        name: "productchangeform",
        params: { no: no },
      });
    },
    movetoadd() {
      this.$router.push("/productadd");
    },
  },
  created() {
    axios.get("/api/productlist").then((result) => {
      this.list = result.data;
    });
  },
  filters: {
    formatPrice(value) {
      if (!value) return "";
      return value.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "$1,");
    },
  },
};
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
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

#myInput {
  background-image: url("../../assets/images/searchIcon2.png");
  background-size: 23px;
  background-position: 8px 8px;
  background-repeat: no-repeat;
  width: 260px;
  height: 35px;
  font-size: 16px;
  padding: 12px 20px 12px 42px;
  border: 1px solid #ddd;
  margin-bottom: 12px;
}
</style>