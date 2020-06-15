import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
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

export default router
