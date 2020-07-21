<template>
  <div id="home" class="contents">
    <h2>제품 조회</h2>
    <br>
    
    <Products />
  </div>
</template>

<script>
import Products from '@/components/doyun/user_admin/subscribe/Products';

export default {
  components: {
    Products,
  }
}
</script>

<style>
  #home {
      text-align: center;
      padding: 40px;
  }
</style>