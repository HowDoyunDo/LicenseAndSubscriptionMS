export default [
    {
        path: '/subscribe/alllist',
        name : 'subscribeAllList',
        component: () => import('@/views/hyeran/system_admin/subscribe/AllListForm.vue'),
    },
    {
        path: '/subscribe/add',
        name : 'subscribeAdd',
        component: () => import('@/views/hyeran/system_admin/subscribe/AddForm.vue'),
    },
]
