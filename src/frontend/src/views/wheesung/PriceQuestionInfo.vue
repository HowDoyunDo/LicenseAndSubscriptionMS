<template>
  <div class="contents">
    <div>
      <h1>견적 상세 정보</h1>
      <br />
      <div id="inner">
        <form class="form">
          <table class="table_add">
            <tr>
              <th>이메일</th>
              <td>{{list.email}}</td>
            </tr>
            <tr>
              <th>이름 [기업명]</th>
              <td>{{list.name}} [{{list.co_name}}]</td>
            </tr>
            <tr>
              <th>문의일</th>
              <td>{{list.reg_date}}</td>
            </tr>
            <tr>
              <th>정책</th>
              <td v-if="list.standard === 'A'">{{list.standard}} (에이전트)</td>
              <td v-if="list.standard === 'U'">{{list.standard}} (사용자)</td>
            </tr>
            <tr>
              <th>문의한 제품</th>
              <td>
                <p v-for="productname in this.product" :key="productname">{{productname}}</p>
              </td>
            </tr>
            <tr>
              <th>인원(수량)</th>
              <td>{{list.count}} 명(대)</td>
            </tr>
            <tr>
              <th>문의 상세 내용</th>
              <td>{{list.contents}}</td>
            </tr>
          </table>
          <br />
          <div style="text-align: center; block: inline">
            <input
              v-if="list.status === 'W'"
              style="border-radius:4px; height: 42px; width:100px;"
              class="cssbtn"
              type="button"
              value="메일 답장"
              @click="goMail(list.no)"
            />
            <input
              style="margin: 0 0 0 20px; border-radius:4px; height: 42px; width:80px;"
              class="cssbtn"
              type="button"
              value="목록"
              @click="goList"
            />
            <br><br>
          </div>
        </form>
      </div>
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
      product: [],
    };
  },
  methods: {
    goList() {
      this.$router.push("/pricequestionlist");
    },
    goMail(no) {
      this.$router.push({
        name: "pricequestionmail",
        params: { no: no },
      });
    },
  },
  created() {
    axios
      .post("/api/pricequestioninfo", {
        no: this.$route.params.no,
      })
      .then((result) => {
        this.list = result.data;
        this.product = JSON.parse(result.data.pname);
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