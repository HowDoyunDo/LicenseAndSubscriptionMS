<template>
  <div id="home" class="contents">
    <h2>제품 조회(로그인 전)</h2>
    <br>
    
    <Products />
  </div>
</template>

<script>
import Products from '@/components/doyun/Products';
import axios from 'axios';

export default {
  components: {
    Products,
  },
  async beforeMount() {
    await axios.get('/api/subscription_policy').then(res => { 
      console.log(res.data) 
    })
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