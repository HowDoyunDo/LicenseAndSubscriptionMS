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
    }
]