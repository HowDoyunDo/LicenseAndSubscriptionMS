<template>
  <div class="contents" style="border-left : 1px solid lightgray;">
    <div>
      <h2>정보 수정</h2>
      <br />
      <form class="form">
        <table class="table_add">
          <tr>
            <th>이메일</th>
            <td>{{ this.list.email }}</td>
          </tr>
          <tr>
            <th>이름</th>
            <td>
              <input type="text" v-model="list.name" />
            </td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td>
              <input type="password" v-model="list.password" />
            </td>
          </tr>
          <tr>
            <th>기업명</th>
            <td>
              <input type="text" v-model="list.co_name" />
            </td>
          </tr>
          <tr>
            <th>기업 대표번호</th>
            <td>
              <input type="text" v-model="list.co_tel" />
            </td>
          </tr>
          <tr>
            <th>기업 주소</th>
            <td>
              <input type="text" v-model="list.co_location" />
            </td>
          </tr>
          <tr>
            <th>가입일</th>
            <td>{{ this.$store.state.userinfo.userInfo.reg_date.substring(0,19) }}</td>
          </tr>
        </table>
        <br />
        <div style="text-align: center;">
          <input class="cssbtn" type="button" value="완료"  @click="changeUserInfo"/>
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
      no: ""
    };
  },
  methods: {
    changeUserInfo() {
      axios
        .post("/api/changeuserinfo", {
          no: this.list.no,
          name: this.list.name,
          password: this.list.password,
          co_name: this.list.co_name,
          co_location: this.list.co_location,
          co_tel: this.list.co_tel,
        })
        .then(result => {
          console.log(result);
          if(result.status == 200){
              axios.post("/api/updatetoken",{
                    password: this.list.password,
                    email: this.$store.state.userinfo.userInfo.email
              }).then(result =>{
                  console.log(result.data)
                    sessionStorage.setItem("license-token", result.data);
                    localStorage.removeItem("vuex");
                this.parseJwt(result.data);
              });
              alert("정보가 수정되었습니다.");
              this.$router.push("/myinfo")
          }
        });
    },
    parseJwt(token) {
      try {
        var base64Url = token.split(".")[1];
        var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
        var jsonPayload = decodeURIComponent(
          atob(base64)
            .split("")
            .map(function(c) {
              return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
            })
            .join("")
        );
        this.$store.commit("userinfo/setUserInfo", JSON.parse(jsonPayload));

        return JSON.parse(jsonPayload);
      } catch (e) {
        console.log(e);
      }
    }
  },
  created() {
    this.no = this.$store.state.userinfo.userInfo.no;
    axios
      .post("/api/myinfo", {
        no: this.no
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