<template>
  <div id="categoryLogin" class="contents">
    <h1>제품 조회 및 구독 신청(로그인 후)</h1>
    <br>

    <Products />
  </div>
</template>

<script>
import Products from '@/components/doyun/user_adminsubscribe/Products';
export default {
    components: {
      Products,
    },
}
</script>

<style scoped>
  h1 {
      font-size: 4.5rem;
      color:#000000D9;
  }
  #categoryLogin {
      width: 85%;
      text-align: center;
      padding: 40px;
  }
</style>