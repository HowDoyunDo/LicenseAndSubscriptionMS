<template>
  <div class="contents">
    <h1>견적 신청</h1>
    <br />
    <div id="inner">
      <form class="form">
        <table class="table_add">
          <tr>
            <th>이메일</th>
            <td>{{email}}</td>
          </tr>
          <tr>
            <th>이름 [기업명]</th>
            <td>{{name}} [{{co_name}}]</td>
          </tr>
          <tr>
            <th>기업 대표번호</th>
            <td>{{co_tel}}</td>
          </tr>
          <tr>
            <th>정책</th>
            <td>
              <select v-model="standard">
                <option value="A">에이전트</option>
                <option value="U">사용자</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>제품 선택</th>
            <td>
              <select v-model="selected" @change="addProduct(selected.no)">
                <option disabled value>제품을 선택해주세요</option>
                <option
                  v-for="product in list"
                  :key="product.no"
                  :value="product"
                >{{product.title}} - {{product.name}}</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>문의 제품</th>
            <td>
              <p v-for="plist in product" :key="plist.name">
                {{plist}}
                <img
                  src="../../assets/images/delete.png"
                  width="13px"
                  height="13px;"
                  @click="deleteProduct(plist)"
                />
              </p>
            </td>
          </tr>
          <tr>
            <th>인원(수량)</th>
            <td>
              <input type="number" min="1" max="9999" value="1" v-model="count" />
            </td>
          </tr>
          <tr>
            <th>문의 상세 내용</th>
            <td>
              <textarea
                style="resize: none; width: 100%; height: 300px;"
                placeholder="문의 내용을 상세하게 입력해 주세요."
                v-model="contents"
              />
            </td>
          </tr>
        </table>
        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="button" value="신청" @click="priceQuestion" style="width:80px; height:42px; border-radius:4px;"/>
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
      email: "",
      name: "",
      co_name: "",
      co_tel: "",
      standard: "A",
      contents: "",
      count: 1,
      list: "",
      selected: "",
      product: [],
      pno: [],
    };
  },
  methods: {
    priceQuestion() {
      if (this.contents === "") {
        alert("문의하실 내용을 입력 해주세요.");
        return;
      }
      axios
        .post("/api/pricequestion", {
          email: this.email,
          name: this.name,
          co_name: this.co_name,
          co_tel: this.co_tel,
          standard: this.standard,
          contents: this.contents,
          count: this.count,
          product: this.pno,
        })
        .then((result) => {
          if (result.status == 200) {
            alert("신청 되었습니다.");
            this.$router.push("/");
          }
        });
    },
    addProduct(n) {
      this.pno.push(n);
      this.product.push(this.selected.title + " - " + this.selected.name);
    },
    deleteProduct(p) {
      this.product.splice(this.product.indexOf(p), 1);
    },
  },
  created() {
    this.no = this.$store.state.userinfo.userInfo.no;
    axios
      .post("/api/myinfo", {
        no: this.no,
      })
      .then((result) => {
        this.email = result.data.email;
        this.name = result.data.name;
        this.co_name = result.data.co_name;
        this.co_tel = result.data.co_tel;
      });
    axios.get("/api/productnamelist").then((result) => {
      this.list = result.data;
    });
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
.cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 120px;
  height: 40px;
}
#inner {
  border: 1px solid #ccc;
  padding: 10px;
}
</style>