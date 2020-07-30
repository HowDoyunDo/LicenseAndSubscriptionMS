<template>
  <div id="home" class="contents">
    <h1>제품 조회</h1>
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

<style scoped>
  h1 {
    font-size: 4.5rem;
    color:#000000D9;
  }
  #home {
      text-align: center;
      padding: 40px;
  }
</style>