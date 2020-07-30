<template>
  <div class="contents">
    <div>
      <h2>내정보</h2>
      <br />
      <form class="form">
        <table class="table_add">
          <tr>
            <th>이메일</th>
            <td>{{ this.list.email }}</td>
          </tr>
          <tr>
            <th>이름</th>
            <td>{{ this.list.name }}</td>
          </tr>
          <tr>
            <th>기업명</th>
            <td>{{ this.list.co_name }}</td>
          </tr>
          <tr>
            <th>기업 대표번호</th>
            <td>{{ this.list.co_tel }}</td>
          </tr>
          <tr>
            <th>기업 주소</th>
            <td>{{ this.list.co_location }}</td>
          </tr>
          <tr>
            <th>가입일</th>
            <td>{{ this.$store.state.userinfo.userInfo.reg_date.substring(0,19) }}</td>
          </tr>
        </table>
        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="button" value="수정" @click="clickChange" />
        </div>
        <br />
        <table v-if="click" class="table_add">
          <tr>
            <th>비밀번호 입력</th>
            <td>
              <input style="height: 30px; width: 400px;" type="password" v-model="pw" />
              <input style="float:right" type="button" class="inputpw" value="확인" @click="chkpw" />
            </td>
          </tr>
        </table>
      </form>
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
      click: false,
      pw: "",
    };
  },
  methods: {
    clickChange() {
      this.click = true;
    },
    chkpw() {
      axios
        .post("/api/chkpw", {
          email: this.list.email,
          password: this.pw,
        })
        .then((result) => {
          if (result.data === "S") {
            this.$router.push("/changeuserinfo");
          } else {
            alert("비밀번호가 일치하지 않습니다.");
          }
        });
    },
  },
  created() {
    this.no = this.$store.state.userinfo.userInfo.no;
    axios
      .post("/api/myinfo", {
        no: this.no,
      })
      .then((result) => {
        this.list = result.data;
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
.inputpw {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 100px;
  height: 30px;
}
</style>