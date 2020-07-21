<template>
  <div class="contents" style="border-left : 1px solid lightgray;">
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
          <input class="cssbtn" type="button" value="수정" @click="goChange" />
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
      list: "",
      no: "",
    };
  },
  methods: {
    goChange() {
      this.$router.push("/changeuserinfo");
    },

  },
  created(){
    this.no = this.$store.state.userinfo.userInfo.no
    console.log(this.no);
    axios.post("/api/myinfo",{
      no: this.no
    }).then(result =>{
      console.log(result.data)
      this.list = result.data
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