<template>
  <div class="contents">
    <h2>정책 프로모션 목록</h2>
    <div class="btnn">
      <button class="submit_btn" @click="promotionAdd">등록</button>
    </div>
    <PromotionPolicyAllList />
  </div>
</template>

<script>
import PromotionPolicyAllList from "@/components/hyeran/promotion/PromotionPolicyAllList.vue";
export default {
  components: {
    PromotionPolicyAllList
  },
  methods: {
    promotionAdd() {
      this.$router.push("/promotion/add");
    }
  }
};
</script>

<style>
.btnn {
  text-align: right;
}
button {
  width: 80px;
}
</style>