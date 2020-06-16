<template>
  <div class="contents">
    <h2>구독 정책 목록</h2>
    <div class="btn">
      <button class="submit_btn">등록</button>
    </div>
    <SubscribeAllList/>
  </div>
</template>

<script>
import SubscribeAllList from '@/components/subscribe/SubscribeAllList.vue';
export default {
  components: {
    SubscribeAllList,
  },
};
</script> 
 
<style>
.btn {
  text-align: right;
}
</style>