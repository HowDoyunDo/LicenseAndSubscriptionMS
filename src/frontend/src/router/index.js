import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import subscribe from '@/router/hyeran/subscribe'

Vue.use(VueRouter)

const routes = [
   ...subscribe ,
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/category/login',
    name: 'CategoryLogin',
    component: () => import('../views/CategoryLogin.vue')
  },
  {
    path: '/license/list',
    name: 'LicenseList',
    component: () => import('../views/LicenseList.vue')
  },
  {
    path: '/batch',
    name: 'Batch',
    component: () => import('../views/Batch.vue')
  },
  {
    path: '/analysis/sales',
    name: 'Batch',
    component: () => import('../views/AnalysisSales.vue')
  },
  {
    path: '/analysis/product',
    name: 'Batch',
    component: () => import('../views/AnalysisProduct.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// router.beforeEach((to, from, next)=>{
//   if( to.meta.auth ){
//    console.log('인증이 필요합니다.') ;
//    next('/');
//    return;
//   }
//   // console.log(to);
//   // console.log(from);
//   // console.log(next);
//   next();
// });
export default router
