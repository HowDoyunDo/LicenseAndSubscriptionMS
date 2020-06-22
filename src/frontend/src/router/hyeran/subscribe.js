export default [
    {
        path: '/test',
        name: 'test',
        component: () => import('@/views/hyeran/test.vue'),

    },
    // 시스템관리자 - 구독 정책 목록
    {
        path: '/subscribe/alllist',
        name: 'subscribeAllList',
        component: () => import('@/views/hyeran/system_admin/subscribe/AllListPage.vue'),
        mata : {auth : true}

    },
    // 시스템 관리자 - 구독 정책 등록
    {
        path: '/subscribe/add',
        name: 'subscribeAdd',
        component: () => import('@/views/hyeran/system_admin/subscribe/AddPage.vue'),
    },
     // 시스템 관리자 - 구독 정책 상세보기
     {
        path: '/subscribe/info/:subscribe_no',
        name: 'subscribeInfo',
        component: () => import('@/views/hyeran/system_admin/subscribe/InfoPage.vue'),
    },

    // 프로모션
    //     /promotion
    // /promotion/add
    // /promotion/modify
    // /promotion/delete
    {
        path: '/promotion',
        name: 'promotion',
        component: () => import('@/views/hyeran/system_admin/promotion/AllListPage.vue'),
    },
    {
        path: '/promotion/add',
        name: 'promotionAdd',
        component: () => import('@/views/hyeran/system_admin/promotion/AddPage.vue'),
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
        component: () => import('@/views/hyeran/user_admin/subscribe/ListPage.vue'),
    },

]
