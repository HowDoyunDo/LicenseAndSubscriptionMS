<template>
  <div class="contents">
    <div>
      <h2 style="text-align:center">제품 수정</h2>
      <br />
      <form class="form" @submit.prevent="productChange">
        <table class="table_add">
          <tr>
            <th>카테고리 선택</th>
            <td>
              <select v-model="selected" class="select-box">
                <option v-for="list in categoryList" :value="list.no" :key="list.no">{{list.title}}</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>제품명</th>
            <td>
              <input type="text" v-model="name" />
            </td>
          </tr>
          <tr>
            <th>제품 설명</th>
            <td>
              <input type="text" v-model="comments" />
            </td>
          </tr>
          <tr>
            <th>제품조회 이미지</th>
            <td>
              <input type="file" />
            </td>
          </tr>
          <tr>
            <th>상세정보 이미지</th>
            <td>
              <input type="file" />
            </td>
          </tr>
          <tr>
            <th>가격</th>
            <td>
              <input type="text" min="0" v-model="price" @keyup="numberFormat" />
            </td>
          </tr>
        </table>
        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="submit" value="수정" />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      no: "",
      list: "",
      categoryList: "",
      name: "",
      comments: "",
      price: "",
      reg_date: "",
      selected: "",
      category_no: ""
    };
  },
  methods: {
    numberFormat(event) {
      var regexp = /[^0-9]/gi;
      let num = event.target.value;
      event.target.value = this.comma(num.replace(regexp, ""));
      this.format = this.comma(this.uncomma(num));
    },
    comma(str) {
      str = String(str);
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
    },
    uncomma(str) {
      str = String(str);
      return str.replace(/[^\d]+/g, "");
    },
    productChange() {
      if (
        this.name == null ||
        this.name == "" ||
        this.comments == null ||
        this.comments == "" ||
        this.price == null ||
        this.price == "" ||
        this.category_no == null ||
        this.category_no == ""
      ){
        alert("양식을 모두 입력해주세요");
        return;
      }
        console.log(this.selected);
      axios
        .post("/api/productchange", {
          no: this.no,
          name: this.name,
          comments: this.comments,
          price: this.price,
          category_no: this.selected
        })
        .then(result => {
          console.log(result);
          if (result.status == 200) {
            alert("수정되었습니다.");
            this.$router.push("/productlist");
          }
        });
    }
  },
  computed: {
    dataInfo: function() {
      return this.$route.params.data;
    }
  },
  created() {
    axios.get("/api/categorylist").then(result => {
      this.categoryList = result.data;
    });
    axios
      .post("/api/productinfo", {
        no: this.$route.params.no
      })
      .then(result => {
        this.no = result.data.no;
        this.name = result.data.name;
        this.comments = result.data.comments;
        this.price = result.data.price;
        this.selected = result.data.category_no;
        this.category_no = result.data.category_no;
      });
  }
};
</script>

<style>
.cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 120px;
  height: 40px;
}
</style>