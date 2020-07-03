import Vue from 'vue'
import VueRouter from 'vue-router'
import routedy from './doyun/routedy'
import subscribe from '@/router/hyeran/subscribe'

Vue.use(VueRouter)

const routes = [
  ...subscribe,
  ...routedy,
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
  scrollBehavior () {
    return { x: 0, y: 0 }
  }
})

export default router
