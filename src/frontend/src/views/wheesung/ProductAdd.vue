<template>
  <div class="contents">
    <div>
      <br>
      <h2 style="text-align:center">제품 등록</h2>
      <br />
      <form class="form" @submit.prevent="productAdd">
        <table class="table_add">
          <tr>
            <th>카테고리 선택</th>
            <td>
              <select v-model="selected" class="select-box">
                <option disabled value>제품을 선택해주세요</option>
                <option
                  v-for="(list) in categoryList"
                  :value="list.no"
                  :key="list.no"
                >{{list.title}}</option>
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
        <br>
        <div style="text-align: center;">
        <input class="cssbtn" type="submit" value="등록" />
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
      name: "",
      comments: "",
      price: "",
      file: "",
      selected: "",
      categoryList: ""
    };
  },
  methods: {
    productAdd() {
      if (
        this.name == null ||
        this.name == "" ||
        this.comments == null ||
        this.comments == "" ||
        this.price == null ||
        this.price == ""
      ) {
        alert("입력 정보들이 입력되었는지 확인해주세요.");
      }
      else if(
        this.selected == null ||
        this.selected == ""
      ) {
        alert("카테고리를 선택 해주세요.")
      } 
      else {
        this.price = this.uncomma(this.format);
        console.log(this.price);
        axios
          .post("/api/productadd", {
            name: this.name,
            comments: this.comments,
            price: this.price,
            category_no: this.selected,
          })
          .then(result => {
            console.log(result.data);
            if(result.status == 200){
              alert("제품을 등록하였습니다.");
              this.$router.push("/productlist")
            }
          });
      }
    },
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
  },
  created() {
    axios.get("/api/categorylist").then(result => {
      this.categoryList = result.data;
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