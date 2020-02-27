import Template from '@/components/parts/template/Template'

import Attendance from '@/components/pages/attendances/Attendance'

export default {
  path: '/attendances',
  component: Template,
  children: [
    {
      path: '',
      component: Attendance,
      meta: { requiresAuth: true }
    }
  ]
}