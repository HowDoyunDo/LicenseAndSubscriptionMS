<template>
  <div id="home" class="contents">
    <h2>제품 조회</h2>
    <br>
    
    <Products />
  </div>
</template>

<script>
import Products from '@/components/doyun/Products';

export default {
  components: {
    Products,
  }
}
</script>

<style>
  #home {
      width: 85%;
      text-align: center;
      border-left: 1px solid lightgray;
      padding: 40px;
  }
</style>