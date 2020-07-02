import Vue from 'vue'
import VueRouter from 'vue-router'
import routedy from './doyun/routedy'
import VueRouter from 'vue-router'
import Home from '../views/doyun/Home.vue'
import subscribe from '@/router/hyeran/subscribe'
import login from '@/router/wheesung/login'

Vue.use(VueRouter)

const routes = [
  ...subscribe,
  ...routedy,
  ...login,
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/category/login',
    name: 'CategoryLogin',
    component: () => import('../views/doyun/CategoryLogin.vue')
  },
  {
    path: '/license/list',
    name: 'LicenseList',
    component: () => import('../views/doyun/LicenseList.vue')
  },
  {
    path: '/batch',
    name: 'Batch',
    component: () => import('../views/doyun/Batch.vue')
  },
  {
    path: '/analysis/sales',
    name: 'Batch',
    component: () => import('../views/doyun/AnalysisSales.vue')
  },
  {
    path: '/analysis/product',
    name: 'Batch',
    component: () => import('../views/doyun/AnalysisProduct.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior () {
    return { x: 0, y: 0 }
  }
})

export default router
