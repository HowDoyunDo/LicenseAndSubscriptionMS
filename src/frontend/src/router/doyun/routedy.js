import Home from '@/views/doyun/Home.vue'

export default [
    {
        path: '/',
        name: 'Home',
        component: Home
      },
      {
        path: '/category/login',
        name: 'CategoryLogin',
        component: () => import('@/views/doyun/CategoryLogin.vue')
      },
      {
        path: '/license/list',
        name: 'LicenseList',
        component: () => import('@/views/doyun/LicenseList.vue')
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
        component: () => import('@/views/doyun/Product.vue')
      }
]
