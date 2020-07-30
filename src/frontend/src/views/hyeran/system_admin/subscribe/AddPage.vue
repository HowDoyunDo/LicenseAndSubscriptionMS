<template>
  <div class="contents">
    <h1>구독 정책 등록</h1>
    <br>
    <SubscribeAdd/>
  </div>
</template>

<script>
import SubscribeAdd from "@/components/hyeran/subscribe/SubscribeAdd.vue";

export default {
  components: {
    SubscribeAdd,
  }
};
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
</style>