<template>
  <div class="contents">
    <h1> 전체 라이선스 현황 </h1>
    <br />
    <LicenseAllList />
  </div>
</template>

<script>
import LicenseAllList from "@/components/hyeran/license/LicenseAllList.vue";
export default {
  components: {
    LicenseAllList
  }
};
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
  button {
    width: 80px;
  }
</style>