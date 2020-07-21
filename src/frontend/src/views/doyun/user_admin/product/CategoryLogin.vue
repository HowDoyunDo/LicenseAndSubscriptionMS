<template>
  <div id="categoryLogin" class="contents">
    <h2>제품 조회 및 구독 신청(로그인 후)</h2>
    <br>

    <Products />
  </div>
</template>

<script>
import Products from '@/components/doyun/user_admin/subscribe/Products';
export default {
    components: {
      Products,
    },
}
</script>

<style>
  #categoryLogin {
      width: 85%;
      text-align: center;
      border-left: 1px solid lightgray;
      padding: 40px;
  }
</style>