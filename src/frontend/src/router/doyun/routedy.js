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
      // {
      //   path: '/license/list',
      //   name: 'LicenseList',
      //   component: () => import('@/views/doyun/user_admin/license/LicenseList.vue')
      // },
      {
        path: '/batch',
        name: 'Batch',
        component: () => import('@/views/doyun/system_admin/batch/Batch.vue')
      },
      {
        path: '/analysis/sales/day',
        name: 'AnalysisSalesDay',
        component: () => import('@/views/doyun/system_admin/analysis/sales/AnalysisSalesDay.vue')
      },
      {
        path: '/analysis/sales/week',
        name: 'AnalysisSalesWeek',
        component: () => import('@/views/doyun/system_admin/analysis/sales/AnalysisSalesWeek.vue')
      },
      {
        path: '/analysis/sales/month',
        name: 'AnalysisSalesMonth',
        component: () => import('@/views/doyun/system_admin/analysis/sales/AnalysisSalesMonth.vue')
      },
      {
        path: '/analysis/product',
        name: 'AnalysisProduct',
        component: () => import('@/views/doyun/system_admin/analysis/product/AnalysisProduct.vue')
      },
      {
        path: '/product',
        name: 'Product',
        component: () => import('@/views/doyun/user_admin/product/Product.vue')
      },
      {
        path: '/subscribe/order/:policyidx/:policytype',
        name: 'SubscribeOrder',
        component: () => import('@/views/doyun/user_admin/product/SubscribeOrder.vue')
      },
      {
        path: '/license/info/:licenseno/:licensetype',
        name: 'LicenseInfo',
        component: () => import('@/views/doyun/user_admin/license/LicenseInfo.vue')
      },
      {
        path: '/adduser',
        name: 'AddUser',
        component: () => import('@/views/doyun/user_admin/license/AddUser.vue')
      },
      {
        path: '/addusers',
        name: 'AddUsers',
        component: () => import('@/views/doyun/user_admin/license/AddUsers.vue')
      },
      {
        path: '/addagent',
        name: 'AddAgent',
        component: () => import('@/views/doyun/user_admin/license/AddAgent.vue')
      },
]
