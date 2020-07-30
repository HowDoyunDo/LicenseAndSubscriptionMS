<template>
  <div class="contents">
    <h1>구독 정책 목록</h1>
    <br />
    <SubscribeAllList />
  </div>
</template>

<script>
import SubscribeAllList from "@/components/hyeran/subscribe/SubscribeAllList.vue";
export default {
  components: {
    SubscribeAllList,
  },
};
</script>

<style scoped>
h1 {
  font-size: 4.5rem;
  color: #000000d9;
}
</style>
