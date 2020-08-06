<template>
  <div class="contents">
    <div>
      <h1>메일 답장</h1>
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
              <th>답변 내용</th>
              <td>
                <textarea style="resize: none; width: 100%; height: 300px;" v-model="contents" />
              </td>
            </tr>
          </table>
          <br />
          <div style="text-align: center; block: inline">
            <input class="cssbtn" type="button" value="전송" @click="emailSend" style="border-radius:4px; height: 42px; width:80px;"/>
            <input
              style="margin: 0 0 0 20px; border-radius:4px; height: 42px; width:80px;"
              class="cssbtn"
              type="button"
              value="목록"
              @click="goList"
            />
          </div>
          <br>
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
      list: "",
      no: "",
      product: [],
      contents: "",
    };
  },
  methods: {
    goList() {
      this.$router.push("/pricequestionlist");
    },
    emailSend() {
      if (this.contents === "") {
        alert("답변 내용을 입력해 주세요.");
      } else {
        axios
          .post("/api/statechange", {
            no: this.$route.params.no,
          })
          .then((result) => {
            if (result.status === 200) {
              alert("전송되었습니다.");
              this.$router.push("/pricequestionlist");
              axios.post("/api/emailsend", {
                email: this.list.email,
                contents: this.contents,
              });
            }
          });
      }
    },
  },
  created() {
    axios
      .post("/api/pricequestionmailform", {
        no: this.$route.params.no,
      })
      .then((result) => {
        this.list = result.data;
        this.product = JSON.parse(result.data.pname);
        this.contents =
          "견적 내용\n\n" +
          "정책 기준(A: 에이전트, U: 사용자)  :  " +
          this.list.standard +
          "\n" +
          "인원(수량)  :  " +
          this.list.count +
          "\n" +
          "문의한 제품  :  " +
          this.product +
          "\n" +
          "문의 상세 내용  :  " +
          this.list.contents +
          "\n\n" +
          "---------------------------------------------------------------------------------------" +
          "\n\n" +
          "답변 내용  :  ";
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