<template>
  <div class="contents">
    <div id="login" style="width: 460px; margin: 0 auto;">
      <br />
      <form @submit.prevent="login">
        <h1 style="text-align:center;">로그인</h1>
        <br />
        <span class="signtext">
          <input class="signtextinput" type="text" placeholder="이메일을 입력하세요." v-model="email" />
        </span>
        <br>
        <span class="signtext">
          <input class="signtextinput" type="password" placeholder="비밀번호를 입력하세요." v-model="password" />
        </span>
        <br>
        <input style="width: 100%; height: 51px; background-color: #3498db; border:none; color: #FFFFFF;" type="submit" value="로그인" />
      </form>
      <br>
      <router-link to="/adminlogin" style="float:right;">관리자 로그인</router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      no: null,
      email: "",
      password: "",
      name: "",
      co_name: "",
      co_location: "",
      co_tel: "",
      co_number: "",
      manager_type: "",
      reg_date: "",
      show: true,
      admin: "active",
      user: ""
    };
  },
  methods: {
    login() {
      if (this.email == "") {
        alert("이메일을 입력해주세요.");
        return;
      }
      if (this.password == "") {
        alert("비밀번호를 입력해주세요.");
        return;
      }
      axios
        .post("/api/login", {
          email: this.email,
          password: this.password
        })
        .then(result => {
          console.log(result);
          if (result.data === "") {
            alert("ID, PASSWORD CHECK PLEASE");
            this.initForm();
            return;
          }
          sessionStorage.setItem("license-token", result.data);
          this.parseJwt(result.data);
          //:policy_no:usemonth:start_date:promotion_type:visible
          if(this.$route.params !== undefined || this.$route.params !== '' ){
            this.$router.push("/subscribe/order-url/"
             +this.$route.params.policy_no +this.$route.params.usemonth 
             + this.$route.params.promotion_type 
             + this.$route.params.visible
            )
          }else{
            this.$router.push("/");
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
        console.log(JSON.parse(jsonPayload));
        this.$store.commit("userinfo/setUserInfo", JSON.parse(jsonPayload));
        console.log(this.$store.state.userinfo.userInfo.name);
        return JSON.parse(jsonPayload);
      } catch (e) {
        console.log(e);
      }
    },
    initForm() {
      this.email = "";
      this.password = "";
    }
  }
};
</script>

<style>
.signtext {
  width: 100%;
  height: 51px;
  padding: 10px 14px;
  border: 1px solid #3498db;
  display: block;
  position: relative;
}
.signtextinput {
  width: 420px;
  height: 30px;
  border: none;
  outline: none;
}
</style>