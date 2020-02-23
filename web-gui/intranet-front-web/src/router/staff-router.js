import Staff from '@/components/pages/staffs/Staff'

import StaffList from '@/components/pages/staffs/staff_list/StaffList'

export default {
  path: '/staffs',
  component: Staff,
  children: [
    {
      path: '',
      component: StaffList
    }
  ]
}