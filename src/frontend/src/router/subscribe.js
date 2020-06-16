// import Vue from 'vue';
// import VueRouter from 'vue-router';

// Vue.use(VueRouter);

export default [
    {
        path: '/subscribe/alllist',
        name : 'subscribeAllList',
        component: () => import('@/views/system_admin/subscribe/AllListForm.vue'),
    },
    {
        path: '/subscribe/add',
        name : 'subscribeAdd',
        component: () => import('@/views/system_admin/subscribe/AddForm.vue'),
    },
]
