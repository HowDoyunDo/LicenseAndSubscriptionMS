<template>
  <div id="monthlyPay">
    <br />
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
var IMP = window.IMP; // 생략해도 괜찮습니다.
IMP.init("imp67730889"); // "imp00000000" 대신 발급받은 "가맹점 식별코드"를 사용합니다.

// Samesite Setting
// document.cookie = "safeCookie1=foo; SameSite=Lax";
// document.cookie = "safeCookie2=foo";
// document.cookie = "crossCookie=bar; SameSite=None; Secure";

export default {
  data() {
    return {};
  },
  methods: {

  },
};
</script>

<style scoped></style>
