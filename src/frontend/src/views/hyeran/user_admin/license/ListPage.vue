<template>
  <div class="contents">
    <h2>{{userInfo.name}} 님의 라이선스 현황</h2>
    <br />
    <LicenseUserListCom :licenses="licenses" />
  </div>
</template>

<script>
import { licenseUserList } from "@/api/shr/license";
import LicenseUserListCom from "@/components/hyeran/license/LicenseUserList.vue";
export default {
  data() {
    return {
      userInfo: "",
      licenses : '',
    };
  },
  components: {
    LicenseUserListCom
  },
  async created() {
    // 로그인 한 사용자 정보
    this.userInfo = this.$store.state.userinfo.userInfo;
    const { data } = await licenseUserList({no : this.userInfo.no});
    this.licenses = data;
  }
};
</script>

<style>
button {
  width: 80px;
}
</style>