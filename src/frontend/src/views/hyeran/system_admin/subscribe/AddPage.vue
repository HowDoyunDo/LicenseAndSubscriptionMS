<template>
  <div class="contents">
    <h1>구독 정책 등록</h1>
    <br />
    <div id="inner">
      <SubscribeAdd />
    </div>
  </div>
</template>

<script>
import SubscribeAdd from "@/components/hyeran/subscribe/SubscribeAdd.vue";

export default {
  components: {
    SubscribeAdd,
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
