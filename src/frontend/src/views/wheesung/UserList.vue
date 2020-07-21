<template>
  <div class="contents" style="border-left : 1px solid lightgray;">
    <div>
      <br />
      <h2 style="text-align:center">관리자 목록</h2>
      <br />
      <table class="table_board">
        <tr style="float:center;">
          <th>번호</th>
          <th style="width:150px; text-align: left;">기업명 [관리자]</th>
          <th>타입</th>
          <th style="width:250px;text-align: left; padding: 0 0 0 20px;">이메일</th>
          <th style="width:150px;">등록일</th>
        </tr>
        <tr
          onmouseover="this.style.background='#CEECF5';"
          onmouseout="this.style.background=''"
          style="cursor:pointer"
          v-for="(user, idx) in list"
          :key="user.no"
          @click="listClick(user.no)"
        >
          <td>{{ idx + 1 }}</td>
          <td style="text-align: left;">{{ user.co_name }} [{{ user.name }}]</td>
          <td>{{ user.manager_type }}</td>
          <td style="text-align: left; padding: 0 0 0 20px;">{{user.email}}</td>
          <td>{{user.reg_date}}</td>
        </tr>
      </table>
    </div>
    <br />
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      no: "",
      list: "",
      title: "",
      ca_title: ""
    };
  },
  methods: {
    listClick(no) {
      this.$router.push({
          name: "userinfo",
          params: {no: no}
      })
    }
  },
  created() {
    axios.get("/api/userlist").then(result => {
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
</style>