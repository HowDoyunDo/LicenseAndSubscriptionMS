<template>
  <div class="contents">
    <h2>프로모션 목록</h2>
    <div class="btnn">
      <button class="submit_btn" @click="promotionAdd">등록</button>
    </div>
    <PromotionAllList />
  </div>
</template>

<script>
import PromotionAllList from "@/components/hyeran/promotion/PromotionAllList.vue";
export default {
  components: {
    PromotionAllList
  },
  methods:{
    promotionAdd(){
      this.$router.push('/promotion/add');
    }
  }
};
</script>

<style>
.btnn {
  text-align: right;
}
</style>