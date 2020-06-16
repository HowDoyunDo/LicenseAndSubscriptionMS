
export default [
    {
        path: '/subscribe/alllist',
        name: 'subscribeAllList',
        component: () => import('@/views/hyeran/system_admin/subscribe/AllListForm.vue'),
    },
    {
        path: '/subscribe/add',
        name: 'subscribeAdd',
        component: () => import('@/views/hyeran/system_admin/subscribe/AddForm.vue'),
    },

    // 프로모션
    //     /promotion
    // /promotion/add
    // /promotion/modify
    // /promotion/delete
    {
        path: '/promotion',
        name: 'promotion',
        component: () => import('@/views/hyeran/system_admin/promotion/AllListForm.vue'),
    },
    {
        path: '/promotion/add',
        name: 'promotionAdd',
        component: () => import('@/views/hyeran/system_admin/promotion/AddForm.vue'),
    },

    // 고객관리자 구독 내역
    //     /subscribe/list
    // /subscribe/info
    // /subscribe/receipt
    // /subscribe/cancel
    // /subscribe/change
    {
        path: '/subscribe/list',
        name: 'subscribeList',
        component: () => import('@/views/hyeran/user_admin/subscribe/ListForm.vue'),
    },

]
