<template>
  <div class="contents">
    <h2>구독 정책 목록</h2>
    <div class="btnn">
      <button class="submit_btn">등록</button>
    </div>
    <PromotionAllList />
  </div>
</template>

<script>
import PromotionAllList from "@/components/hyeran/promotion/PromotionAllList.vue";
export default {
  components: {
    PromotionAllList
  }
};
</script>

<style>
.btnn {
  text-align: right;
}
</style>