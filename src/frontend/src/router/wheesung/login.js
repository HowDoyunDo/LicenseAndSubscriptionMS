
export default [
    {
        path: '/signup',
        name: 'signup',
        component: () => import('@/views/wheesung/Signup.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/wheesung/Login.vue')
    },
    {
        path: '/adminlogin',
        name: 'adminlogin',
        component: () => import('@/views/wheesung/AdminLogin.vue')
    },
    {
        path: '/categorylist',
        name: 'categorylist',
        component: () => import('@/views/wheesung/CategoryList.vue')
    },
    {
        path: '/productlist',
        name: 'productlist',
        component: () => import("@/views/wheesung/ProductList.vue")
    },
    {
        path: '/productadd',
        name: 'productadd',
        component: () => import("@/views/wheesung/ProductAdd.vue")
    },
    {
        path: '/productchangeform/:no',
        name: 'productchangeform',
        component: () => import("@/views/wheesung/ProductChange.vue")
    },
    {
        path: '/userlist',
        name: 'userlist',
        component: () => import("@/views/wheesung/UserList.vue")
    },
    {
        path: '/userinfo/:no',
        name: 'userinfo',
        component: () => import("@/views/wheesung/UserInfo.vue")
    },
    {
        path: '/subscriptioncancelchange',
        name: 'subscriptioncancelchange',
        component: () => import("@/views/wheesung/SubscriptionCancelChange.vue")
    },
    {
        path: '/orderlist',
        name: 'orderlist',
        component: () => import("@/views/wheesung/OrderList.vue")
    },
    {
        path: '/orderinfo/:no',
        name: 'orderinfo',
        component: () => import("@/views/wheesung/OrderInfo.vue")
    }
]