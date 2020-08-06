<template>
  <div class="contents">
    <h1>정책 프로모션 목록</h1>
    <br>
    <PromotionPolicyAllList />
  </div>
</template>

<script>
import PromotionPolicyAllList from "@/components/hyeran/promotion/PromotionPolicyAllList.vue";
export default {
  components: {
    PromotionPolicyAllList,
  },
  methods: {
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
.btnn {
  text-align: right;
}
button {
  width: 80px;
}
</style>
