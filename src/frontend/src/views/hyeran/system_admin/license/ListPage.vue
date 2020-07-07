<template>
  <div class="contents">
    <h2> 전체 라이선스 현황 </h2>
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

<style>
button {
  width: 80px;
}
</style>