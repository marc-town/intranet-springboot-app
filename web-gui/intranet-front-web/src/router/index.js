import Vue from 'vue'
import Router from 'vue-router'

import store from '@/store/index.js'

// import routers
import AttendanceRoute from './attendance-router'
import BlogRoute from './blog-router'
import ScheduleRoute from './schedule-router'
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
    { ...BlogRoute },
    { ...ScheduleRoute },
    { ...StaffRoute },
    { path: '/login', component: Login, meta: { isPublic: true } },
    { path: '/error', component: ErrorPage },
    { path: '*', redirect: '/error' }
  ]
})

router.beforeEach((to, from, next) => {
  const token = store.state.auth.token;
  const isPublic = to.matched.some(page => page.meta.isPublic);
  const toPage = to.path;
  if (isPublic || token) {
    next()
  } else {
    if (toPage === '/') next({ path: '/login' });
    else next({path: '/login', query: {backuri: to.fullPath}});
  }
})

export default router