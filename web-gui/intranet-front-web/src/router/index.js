import Vue from 'vue'
import Router from 'vue-router'

import store from '@/store/index.js'

// import routers
import AttendanceRoute from './attendance-router'
import StaffRoute from './staff-router'

// import components
import Index from '@/components/pages/Index'

import Login from '@/components/pages/auth/signin/Signin'
import ErrorPage from '@/components/pages/error/Error'

import Template from '@/components/parts/template/Template'

Vue.use(Router)

const router = new Router({
  mode: "history", // URLはハッシュ形式「#～～」ではなく「/～～」とする
  base: process.env.BASE_URL,
  routes: [
    { path: '/', component: Template, children: [{ path: '', component: Index }] },
    { ...AttendanceRoute },
    { ...StaffRoute },
    { path: '/login', component: Login, meta: { isPublic: true } },
    { path: '/error', component: ErrorPage },
    { path: '*', redirect: '/error' }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(page => page.meta.isPublic) || store.state.auth.token) {
    next()
  } else {
    next({path: '/login', query: {backuri: to.fullPath}})
  }
})

export default router