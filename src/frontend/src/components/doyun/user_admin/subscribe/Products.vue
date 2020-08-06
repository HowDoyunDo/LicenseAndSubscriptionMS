<template>
  <div id="products">
    <ul>
      <li v-for="product in productList" v-bind:key="product.no">
        <div class="panel panel-info" @click="detail(product)">
          <router-link to='/product' style="text-decoration: none;">
            <div class="panel-heading">
              {{ product.name }}
              <img src='@/assets/arrow_01.png' align="right"/>
            </div>
            <div class="panel-body" :style='{ backgroundImage: "url(" + product.product_image + ")", }'>
              <img class="solutions" :src="product.category_image">
              <p>
                <br><br>
                {{ product.title }}
                <br>
                ―
              </p>
              <br>
              <!-- 사용자 패턴에 맞는 UI구성. 다양한 업무의 효율적 수용<br>데이터 연동 및 다양한 사용자 편의 기능 -->
              {{ product.comments }}
            </div>
          </router-link>
        </div>  
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            productList: '',
        }
    },
    methods: {
        async detail(product) {
            this.$store.commit('productStore/CHANGE_PDT', product);

            await axios.get('/api/productsInCategory', {
                params: {
                    sol: product.title
                }
            }).then(res => { 
                this.$store.commit('productStore/CHANGE_TABS', res.data);
            });
            
            // solution store와 일치하는 User구독정책 table에서 검색
            await axios.get('/api/userpolicylist', {
                params: {
                    sol : product.title
                }
            }).then(res => { 
                this.$store.commit('productStore/CHANGE_UPLCY', res.data);
                console.log(this.$store.state.productStore.uPolicies);
            });
            // solution store와 일치하는 Agent구독정책 table에서 검색
            await axios.get('/api/agentpolicylist', {
                params: {
                    sol : product.title
                }
            }).then(res => { 
                this.$store.commit('productStore/CHANGE_APLCY', res.data);
                console.log(this.$store.state.productStore.aPolicies);
            });
        }
    },
    computed: {
        solution: function() {
            return this.$store.state.productStore.solution;
        },
        product: function() {
            return this.$store.state.productStore.product;
        },
    },
    async created() {
        await axios.get('/api/getProductList', {
        }).then(res => {
            this.productList = res.data;
        });
    }
}
</script>

<style scoped>
.panel:hover .panel-body {
  background-color: #D9EDF7;
  background-blend-mode: multiply;
}
</style>