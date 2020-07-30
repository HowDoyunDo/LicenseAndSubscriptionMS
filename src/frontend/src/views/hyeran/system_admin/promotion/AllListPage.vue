<template>
  <div class="contents">
    <h1>제품 프로모션 목록</h1>
    <br>
    <PromotionAllList />
  </div>
</template>

<script>
import PromotionAllList from "@/components/hyeran/promotion/PromotionAllList.vue";
export default {
  components: {
    PromotionAllList,
  },
};
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
</style>