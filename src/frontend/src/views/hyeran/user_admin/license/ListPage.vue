<template>
  <div class="contents">
    <h2>{{userInfo.name}} 님의 라이선스 현황</h2>
    <br />
    <LicenseUserList :userInfo="userInfo" />
  </div>
</template>

<script>
import LicenseUserList from "@/components/hyeran/license/LicenseUserList.vue";
export default {
  data() {
    return {
      userInfo: ""
    };
  },
  components: {
    LicenseUserList
  },
  created() {
    // 로그인 한 사용자 정보
    this.userInfo = this.$store.state.userinfo.userInfo;
  }
};
</script>

<style>
</style>