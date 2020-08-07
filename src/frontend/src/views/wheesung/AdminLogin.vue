<template>
  <div class="contents">
    <div id="login" style="width: 460px; margin: 0 auto;">
      <form @submit.prevent="login">
        <h1 style="text-align:center;">관리자 로그인</h1>
        <br />
        <span class="signtext">
          <input class="signtextinput" type="text" placeholder="이메일을 입력하세요." v-model="email" />
        </span>
        <span class="signtext">
          <input
            class="signtextinput"
            type="password"
            placeholder="비밀번호를 입력하세요."
            v-model="password"
          />
        </span>
        <input
          style="width: 100%; height: 51px; background-color: #3498db; border:none; color: #FFFFFF; border-radius: 4px;"
          type="submit"
          value="로그인"
        />
      </form>
      <br>
      <router-link to="/login" style="float:right;">사용자 로그인</router-link>
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
        }
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
                .post("/api/adminlogin", {
                email: this.email,
                password: this.password
                })
            .then(result => {
                if (result.data === "") {
                    alert("ID, PASSWORD CHECK PLEASE");
                    this.initForm();
                    return;
                }
                sessionStorage.setItem("license-token", result.data);
                this.parseJwt(result.data);
                this.$router.push("/analysis/sales/day");
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
        },
        initForm() {
            this.email = "";
            this.password = "";
        }
    }
};
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
.signtext {
  width: 100%;
  height: 51px;
  padding: 10px 14px;
  border: 1px solid #ccc;
  display: block;
  position: relative;
  border-radius: 4px;
  margin-bottom: 10px;
}
.signtextinput {
  width: 420px;
  height: 30px;
  border: none;
  outline: none;
}
</style>