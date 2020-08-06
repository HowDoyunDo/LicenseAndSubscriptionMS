<template>
  <div class="contents">
    <h1>프로모션 등록</h1>
    <br />
    <div id="inner">
      <PromotionAdd />
    </div>
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
#inner {
  width: 100%;
  display: inline-block;
  border: 1px solid #ccc;
  padding: 10px;
}
</style>
