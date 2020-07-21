<template>
  <div id="signup" class="contents">
    <h2 style="text-align:center;">회원 가입</h2>
    <br />
    <form @submit.prevent="signin">
      <div style="width: 460px; margin: 0 auto;">
        <div style="width: 100%;">
          <h4>
            <label id="stylelabel">이메일</label>
          </h4>
          <div id="email">
            <span id="inputemail">
              <input style="width:280px; border: none; outline:none;" type="email" v-model="email" @keyup="reset" />
              <img
                src="@/assets/images/check.png"
                v-show="show"
                style="visibility: show ? 'visible' : 'hidden';"
              />
            </span>
            <span>
              <input class="inputbtn" type="button" value="중복확인" @click="emailchk" />
            </span>
          </div>
          <p v-if="show === ''"></p>
          <p v-if="show === true"></p>
          <p v-if="show === false" style="color: #FF0000;">* 다른 이메일을 사용해 주세요.</p>
          <div id="email">
            <span id="inputemail">
              인증번호
              <input style="margin: 0 10px; width: 120px;" type="text" v-model.number="auth_number" />
              <img
                src="@/assets/images/check.png"
                v-show="emailshow"
                style="margin: 0 15px 0 0; visibility: emailshow ? 'visible' : 'hidden';"
              />
              <input type="button" style="background-color: #3498db; border: none; color: #ffffff;" value="전송" @click="emailAuth" />
            </span>
            <span>
              <input class="inputbtn" type="button" value="확인" @click="authNumberChkForm" />
            </span>
          </div>
          <h4>
            <label id="stylelabel">비밀번호</label>
          </h4>
          <span id="signtext">
            <input class="signtextinput" type="password" v-model="password" />
          </span>
          <h4>
            <label id="stylelabel">이름</label>
          </h4>
          <span id="signtext">
            <input class="signtextinput" type="text" v-model="name" />
          </span>
          <h4>
            <label id="stylelabel">사업자 번호</label>
          </h4>
          <span id="signtext">
            <input class="signtextinput" type="text" v-model="co_number" />
          </span>
          <h4>
            <label id="stylelabel">기업명</label>
          </h4>
          <span id="signtext">
            <input class="signtextinput" type="text" v-model="co_name" />
          </span>
          <h4>
            <label id="stylelabel">기업 대표번호</label>
          </h4>
          <span id="signtext">
            <input class="signtextinput" type="text" v-model="co_tel" />
          </span>
          <h4>
            <label id="stylelabel">기업 주소</label>
          </h4>
          <span id="signtext">
            <input class="signtextinput" type="text" v-model="co_location" />
          </span>
          <br />
          <div style="text-align:center">
            <input type="checkbox" v-model="chk" />
            개인정보 수집에 동의하시겠습니까?
            <br />
            <br />
            <input id="cssbtn" style="width:200px; height: 40px;" type="submit" value="가입하기" />
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      email: "",
      password: "",
      name: "",
      co_name: "",
      co_location: "",
      co_tel: "",
      co_number: "",
      manager_type: "",
      show: "",
      emailshow: "",
      chk: "",
      auth_number: "",
      auth_number_chk : ""
      
    };
  },
  methods: {
    signin() {
      if (
        this.email == null ||
        this.email == "" ||
        this.password == null ||
        this.password == "" ||
        this.name == null ||
        this.name == "" ||
        this.co_name == null ||
        this.co_name == "" ||
        this.co_location == null ||
        this.co_location == "" ||
        this.co_tel == null ||
        this.co_tel == "" ||
        this.co_number == null ||
        this.co_number == ""
      ) {
        alert("필수 정보를 입력해 주세요");
        console.log(this.chk);
      } else if (this.show === false || this.show === '') {
        alert("이메일 중복체크를 하세요.");
      } else if (this.chk == false) {
        alert("개인정보 동의를 체크해주세요.");
      } else if (this.auth_number==""){
        alert("인증번호를 입력 해주세요.");
      } else if (this.auth_number_chk=="" || this.auth_number != this.auth_number_chk){
        alert("인증되지 않은 번호입니다.")
      }
        else {
        axios
          .post("/api/signup", {
            email: this.email,
            password: this.password,
            name: this.name,
            co_name: this.co_name,
            co_location: this.co_location,
            co_tel: this.co_tel,
            co_number: this.co_number,
            manager_type: this.manager_type
          })
          .then(result => {
            console.log(result.data);
            alert("회원가입 되었습니다.");
            this.$router.replace("/login");
          });
      }
    },
    emailchk() {
      if(!this.validEmail(this.email)){
        this.show = "";
        alert("이메일 형식을 확인하세요.")
      }
      else{
        axios
        .post("/api/emailchk", {
          email: this.email
        })
        .then(result => {
          console.log(result);
          if (result.data == "s") {
            this.show = true;
          } else if (result.data == "f") {
            this.show = false;
          }
        });
      }
    },
    emailAuth(e) {
      if(this.show == true){
        e.target.value = '재전송';
        axios
          .post("/api/emailauth", {
            email: this.email
          })
          .then(result => {
            console.log("번호 : " + result.data.text.substr(17,5));
            this.auth_number_chk = Number(result.data.text.substr(17,5));
          });
      }
      else{
        alert("이메일 중복 확인 하세요.");
      }
    },
    authNumberChkForm(){
      if(this.auth_number == ""){
        alert("인증번호를 입력 해주세요.")
      }
      else if(this.auth_number== this.auth_number_chk){
        alert('인증완료')
        this.emailshow = true;
      }else{
        alert('인증번호 맞지 않음')
      }
    },
    validEmail: function(email){
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(String(email).toLowerCase());
    },
    reset(){
      this.show = "";
      this.auth_number = "";
      this.auth_number_chk = "";
      this.emailshow = "";
    }
  }
};
</script>

<style>
#signtext {
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
#email {
  height: 51px;
  padding: 0 125px 0 0;
  position: relative;
}
#inputemail {
  display: inline-block;
  border: 1px solid #3498db;
  width: 100%;
  padding: 10px 15px 10px 14px;
  vertical-align: top;
  outline: none;
}
.inputbtn {
  position: absolute;
  top: 0;
  right: 0;
  width: 115px;
  height: 51px;
  padding: 16px 0;
  text-align: center;
  box-sizing: border-box;
  text-decoration: none;
  border: none;
  background-color: #3498db;
  color: #ffffff;
}
#stylelabel {
  font-size: 14px;
  font-weight: 700;
}
#cssbtn {
  background-color: #3498db;
  color: #ffffff;
  border: none;
  width: 80px;
  height: 30px;
  align-content: right;
}
#cssbtn .btn {
  width: 20%;
}
</style>