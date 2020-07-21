<template>
  <div class="contents" style="border-left : 1px solid lightgray;">
    <div>
      <h2>관리자 정보</h2>
      <br />
      <form class="form" @submit.prevent="productChange">
        <table class="table_add">
          <tr>
            <th>기업명</th>
            <td>{{ this.list.co_name }}</td>
          </tr>
          <tr>
            <th>관리자명 / 타입</th>
            <td>{{ list.name }} / {{ list.manager_type }}</td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>{{ list.email }}</td>
          </tr>
          <tr>
            <th>기업 주소</th>
            <td>{{ list.co_location }}</td>
          </tr>
          <tr>
            <th>기업 대표번호</th>
            <td>{{ list.co_tel }}</td>
          </tr>
          <tr>
            <th>가입일</th>
            <td>{{ list.reg_date }}</td>
          </tr>
        </table>
        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="button" value="목록" @click="goList" />
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
      list: ""
    };
  },
  methods: {
    goList() {
      this.$router.push("/userlist");
    }
  },
  created() {
    axios
      .post("/api/userinfo", {
        no: this.$route.params.no
      })
      .then(result => {
        this.list = result.data;
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