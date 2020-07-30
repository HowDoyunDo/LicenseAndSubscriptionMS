<template>
  <div class="contents">
    <div>
      <h1>관리자 목록</h1>
      <br />
      <table class="table_board">
        <tr style="float:center;">
          <th width="5%">번호</th>
          <th style="width:20%;">기업명 [관리자]</th>
          <th width="5%">타입</th>
          <th style="width:20%; padding: 0 0 0 20px;">이메일</th>
          <th style="width:15%;">등록일</th>
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
          <td>{{ user.co_name }} [{{ user.name }}]</td>
          <td>{{ user.manager_type }}</td>
          <td style="padding: 0 0 0 20px;">{{user.email}}</td>
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

<style scoped>
@import "/assets/css/allList.css";

  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
.cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 200px;
  height: 40px;
}
</style>