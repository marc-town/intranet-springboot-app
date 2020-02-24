import Vue from 'vue'
import Router from 'vue-router'

import store from '@/store/index.js'

// import routers
import AttendanceRoute from './attendance-router'
import StaffRoute from './staff-router'

// import components
import Index from '@/components/pages/Index'

import Login from '@/components/pages/auth/signin/Signin'
import Error from '@/components/pages/error/Error'

// TODO
import Template from '@/components/parts/template/Template'

Vue.use(Router)

const router = new Router({
  mode: "history", // URLはハッシュ形式「#～～」ではなく「/～～」とする
  base: process.env.BASE_URL,
  routes: [
    { path: '/', component: Index },
    { ...AttendanceRoute },
    { ...StaffRoute },
    { path: '/template', component: Template, meta: { requiresAuth: false } },
    { path: '/login', component: Login, meta: { requiresAuth: false } },
    { path: '/error', component: Error },
    { path: '*', redirect: '/error' }
  ]
})

router.beforeEach((to, from, next) => {
  if (!to.matched.some(page => page.meta.requiresAuth) || store.state.auth.token) {
    next()
  } else {
    next({path: '/login', query: {backuri: to.fullPath}})
  }
})

export default router