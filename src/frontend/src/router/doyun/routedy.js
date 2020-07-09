import Home from '@/views/doyun/Home.vue'

export default [
      {
        path: '/',
        name: 'Home',
        component: Home
      },
      // {
      //   path: '/category/login',
      //   name: 'CategoryLogin',
      //   component: () => import('@/views/doyun/product/CategoryLogin.vue')
      // },
      {
        path: '/license/list',
        name: 'LicenseList',
        component: () => import('@/views/doyun/license/LicenseList.vue')
      },
      {
        path: '/batch',
        name: 'Batch',
        component: () => import('@/views/doyun/Batch.vue')
      },
      {
        path: '/analysis/sales',
        name: 'AnalysisSales',
        component: () => import('@/views/doyun/AnalysisSales.vue')
      },
      {
        path: '/analysis/product',
        name: 'AnalysisProduct',
        component: () => import('@/views/doyun/AnalysisProduct.vue')
      },
      {
        path: '/product',
        name: 'Product',
        component: () => import('@/views/doyun/product/Product.vue')
      },
      {
        path: '/subscribe/order/:policyidx/:policytype',
        name: 'SubscribeOrder',
        component: () => import('@/views/doyun/product/SubscribeOrder.vue')
      },
      {
        path: '/license/info/:licenseno/:licensetype',
        name: 'LicenseInfo',
        component: () => import('@/views/doyun/license/LicenseInfo.vue')
      },
      {
        path: '/adduser',
        name: 'AddUser',
        component: () => import('@/views/doyun/license/AddUser.vue')
      },
      {
        path: '/addusers',
        name: 'AddUsers',
        component: () => import('@/views/doyun/license/AddUsers.vue')
      },
      {
        path: '/addagent',
        name: 'AddAgent',
        component: () => import('@/views/doyun/license/AddAgent.vue')
      },
]
