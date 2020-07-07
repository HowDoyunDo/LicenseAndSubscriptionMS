<template>
  <div>
    hihihi
    <button @click="test">dd</button>
  </div>
</template>

<script>
export default {
  methods: {
    test() {
        console.log('button');
        // this.$router.push('/subscribe/order-url/1/1/2/1')
    }
  },
//   watch: {
//     $route(to, from) {
//       // 경로 변경에 반응.
//       console.log("totototo : ", to);
//       console.log("fomromfrom: ", from);
//     }
//   }
};
</script>

<style>
</style>