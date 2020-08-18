<template>
  <div class="contents">
    <div>
      <h1>제품 수정</h1>
      <br />
      <form class="form" @submit.prevent="productChange">
        <table class="table_add">
          <tr>
            <th>카테고리 선택</th>
            <td>
              <select v-model="selected" class="select-box">
                <option
                  v-for="list in categoryList"
                  :value="list.no"
                  :key="list.no"
                  >{{ list.title }}</option
                >
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
              <input
                type="file"
                id="productimage"
                @change="inputProductImage"
                accept="image/gif, image/jpeg, image/png, image/jpg"
              />
              <br />
              [현재 이미지]
              <br />
              <img :src="this.product_image" width="100px" height="auto" />
            </td>
          </tr>
          <tr>
            <th>상세정보 이미지</th>
            <td>
              <input
                type="file"
                id="infoimage"
                @change="inputInfoImage"
                accept="image/gif, image/jpeg, image/png, image/jpg"
              />
              <br />
              [현재 이미지]
              <br />
              <img :src="this.info_image" width="100px" height="auto" />
            </td>
          </tr>
        </table>

        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="submit" value="수정" style="border-radius: 4px"/>
          <input
            type="button"
            value="취소"
            class="cssbtn"
            style="margin: 0 0 0 20px; border-radius: 4px"
            @click="movelist"
          />
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
      price: "0",
      reg_date: "",
      selected: "",
      category_no: "",
      product_image: "",
      info_image: "",
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
    // 제품 수정
    productChange() {
      if (
        this.name == null ||
        this.name == "" ||
        this.comments == null ||
        this.comments == "" ||
        this.category_no == null ||
        this.category_no == ""
      ) {
        alert("양식을 모두 입력해주세요");
        return;
      }
      axios
        .post("/api/productchange", {
          no: this.no,
          name: this.name,
          comments: this.comments,
          price: this.price,
          category_no: this.selected,
          info_image: this.info_image,
          product_image: this.product_image,
        })
        .then((result) => {
          if (result.status == 200) {
            alert("수정되었습니다.");
            this.$router.push("/productlist");
          }
        });
    },
    inputProductImage(e) {
      if (e.target.files[0] == undefined) {
        this.product_image = "";
      }
      if (e.target.files.length > 0) {
        if (
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "gif" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "jpg" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "jpeg" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "png"
        ) {
          const file = e.target.files[0];
          const reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = (e) => {
            this.product_image = e.target.result;
          };
        } else {
          alert("이미지 파일을 올려주세요.");
          this.product_image = "";
          document.getElementById("productimage").value = "";
        }
      }
    },
    inputInfoImage(e) {
      if (e.target.files[0] == undefined) {
        this.info_image = "";
      }
      if (e.target.files.length > 0) {
        if (
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "gif" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "jpg" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "jpeg" ||
          e.target.files[0].name
            .split(".")
            .pop()
            .toLowerCase() == "png"
        ) {
          const file = e.target.files[0];
          const reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onload = (e) => {
            this.info_image = e.target.result;
          };
        } else {
          alert("이미지 파일을 올려주세요.");
          this.info_image = "";
          document.getElementById("infoimage").value = "";
        }
      }
    },
    movelist() {
      this.$router.push("/productlist");
    },
  },
  computed: {
    dataInfo: function() {
      return this.$route.params.data;
    },
  },
  created() {
    axios.get("/api/categorylist").then((result) => {
      this.categoryList = result.data;
    });
    axios
      .post("/api/productinfo", {
        no: this.$route.params.no,
      })
      .then((result) => {
        this.no = result.data.no;
        this.name = result.data.name;
        this.comments = result.data.comments;
        this.price = result.data.price;
        this.selected = result.data.category_no;
        this.category_no = result.data.category_no;
        this.product_image = result.data.product_image;
        this.info_image = result.data.info_image;
      });
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
  width: 120px;
  height: 40px;
}
</style>
