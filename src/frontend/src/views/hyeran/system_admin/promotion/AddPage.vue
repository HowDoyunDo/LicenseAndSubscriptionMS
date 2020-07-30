<template>
  <div class="contents">
    <h1>프로모션 등록</h1>
    <br />
    <PromotionAdd />
  </div>
</template>

<script>
import PromotionAdd from "@/components/hyeran/promotion/PromotionAdd.vue";
export default {
  components: {
    PromotionAdd,
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
</style>
