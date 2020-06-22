import Vue from 'vue'
import VueRouter from 'vue-router'
import subscribe from './hyeran/subscribe'
import routedy from './doyun/routedy'

Vue.use(VueRouter)

const routes = [
  ...subscribe,
  ...routedy
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
