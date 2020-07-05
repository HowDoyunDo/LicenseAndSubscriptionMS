<template>
  <div class="contents">
    <h2>구독 정책 목록</h2>
    <div class="btnn">
      <button class="submit_btn" @click="subscribeAdd">등록</button>
    </div>
    <SubscribeAllList />
  </div>
</template>

<script>
import SubscribeAllList from "@/components/hyeran/subscribe/SubscribeAllList.vue";
export default {
  components: {
    SubscribeAllList
  },
  methods: {
    subscribeAdd() {
      this.$router.push("/subscribe/add");
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