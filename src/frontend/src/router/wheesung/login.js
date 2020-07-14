
export default [
    {
        path: '/signup',
        name: 'signup',
        component: () => import('@/views/wheesung/Signup.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/wheesung/Login.vue'),
        children: [
            {
                path: ':policy_no/:usemonth/:promotion_type/:visible',
                component: () => import('@/views/wheesung/Login.vue'),
            },
        ]
    },
    // {
    //     path: '/login/:policy_no/:usemonth/:promotion_type/:visible',
    //     name: 'login',
    //     component: () => import('@/views/wheesung/Login.vue'),
      
    // },
    {
        path: '/adminlogin',
        name: 'adminlogin',
        component: () => import('@/views/wheesung/AdminLogin.vue')
    }
]