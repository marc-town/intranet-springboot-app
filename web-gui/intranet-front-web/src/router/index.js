import Vue from 'vue'
import Router from 'vue-router'

// import routers
import AttendanceRoute from './attendance-router'
import StaffRoute from './staff-router'

// import components
// import Index from '@/components/pages/Index'
import Hello from '@/components/HelloWorld'

Vue.use(Router)

const routes = [
  { path: '/', component: Hello },
  { ...AttendanceRoute },
  { ...StaffRoute }
]

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: routes
})