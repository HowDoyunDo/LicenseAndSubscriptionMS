<template>
  <div id="header">
    <router-link to="/" v-if="this.userInfo.name === undefined"
      ><img
        style="width:155px; height:25px; margin-left:16px" @click="clrMenu()"
        src="../../public/logo.png"
    /></router-link>
    <router-link to="/" v-if="this.userInfo.co_number !== undefined"
      ><img
        style="width:155px; height:25px; margin-left:16px" @click="clrMenu()"
        src="../../public/logo.png"
    /></router-link>
    <router-link
      to="/analysis/sales/day"
      v-if="
        this.userInfo.name !== undefined &&
          this.userInfo.co_number === undefined
      "
      ><img
        style="width:155px; height:25px; margin-left:16px" @click="clrMenu()"
        src="../../public/logo.png"
    /></router-link>

    <div v-if="alertOverLicense.length !== 0" style="float: right; margin:37px 20px 0 0;">
      <router-link id="license" to="/license/list" style="text-decoration:none;" @click.native="clrMenu(16)">| 기간 만료 15일 이내 라이선스 존재 |</router-link>
    </div>

    <ul
      v-if="
        this.userInfo.name !== undefined &&
          this.userInfo.co_number === undefined
      "
    >
      <li @click="remove">
        <router-link id="logout" to="/" @click.native="clrMenu()">로그아웃</router-link>
      </li>
      <li>관리자님 환영합니다.</li>
    </ul>
    <ul v-else-if="this.userInfo.co_number !== undefined">
      <li @click="remove">
        <router-link id="logout" to="/" @click.native="clrMenu()">로그아웃</router-link>
      </li>
      <li id="white">{{ this.userInfo.name }}님 어서오세요.</li>
    </ul>
    <ul v-else-if="this.userInfo.name === undefined">
      <li><router-link id="login" to="/login">로그인</router-link></li>
      <li>|</li>
      <li><router-link id="signup" to="/signup">회원가입</router-link></li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: "",
    };
  },
  methods: {
    remove() {
      this.$store.commit("licenseStore/SELECT_AOL", []);
      console.log(this.userInfo.name);
      this.$store.commit("userinfo/setUserInfo", "");
      localStorage.removeItem("vuex");
      sessionStorage.removeItem("license-token");
      console.log(this.userInfo.name);
    },
    clrMenu(value) {
      this.$store.commit("menuStore/SEL_MENU", value);
    }
  },
  computed: {
    userInfo: function() {
      return this.$store.state.userinfo.userInfo;
    },
    alertOverLicense: function() {
      return this.$store.state.licenseStore.alertOverLicense;
    }
  },
};
</script>

<style scoped>
div #header {
  height: 100px;
  background-color: #103366;
  /* background-color: #b0544f; */
  margin: 0;
}
div #header h4 {
  text-align: right;
  color: white;
  vertical-align: bottom;
  margin: 0 10px 10px 0;
}

div #header img {
  display: inline-block;
  margin: 35px 0 0 10px;
}

div #header ul {
  list-style: none;
  color: white;
  vertical-align: bottom;
  margin: 0 10px 10px 0;
}
div #header li {
  float: right;
  color: white;
  margin-right: 10px;
}

#login, #logout, #signup {
  color: white;
  text-decoration: none;
}
#login:hover, #logout:hover, #signup:hover, #license:hover {
  color: #3498db;
}
#license {
  color: rgba(255, 99, 132, 1);
}
</style>
