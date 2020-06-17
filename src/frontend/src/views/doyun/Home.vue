<template>
  <div id="home" class="contents">
    <h2>제품 조회(로그인 전)</h2>
    <br>
    
    <Products />
  </div>
</template>

<script>
import Products from '@/components/doyun/products';
import axios from 'axios';

export default {
  components: {
    Products,
  },
  beforeMount() {
    axios.get('/api/category').then(res => { 
      console.log(res.data) 
    })
  }

  
}
</script>

<style>
</style>