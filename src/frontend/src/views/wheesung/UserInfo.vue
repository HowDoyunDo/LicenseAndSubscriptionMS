<template>
  <div class="contents">
    <div>
      <h1>관리자 정보</h1>
      <br />
      <div id="inner">
        <form class="form">
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
            <input
              class="cssbtn"
              type="button"
              value="목록"
              @click="goList"
              style="border-radius:4px"
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
      no: "",
      list: "",
    };
  },
  methods: {
    // 사용자 목록으로 이동
    goList() {
      this.$router.push("/userlist");
    },
  },
  created() {
    axios
      .post("/api/userinfo", {
        no: this.$route.params.no,
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
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
  padding:10px;
}
</style>
