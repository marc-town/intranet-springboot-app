// import Staff from '@/components/pages/staffs/Staff'
import Template from '@/components/parts/template/Template'

import StaffList from '@/components/pages/staffs/staff_list/StaffList'

export default {
  path: '/staffs',
  component: Template,
  children: [
    {
      path: '',
      component: StaffList,
      meta: { requiresAuth: false }
    }
  ]
}