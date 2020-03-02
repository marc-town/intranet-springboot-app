import Template from '@/components/parts/template/Template'

import StaffList from '@/components/pages/staffs/staff_list/StaffList'
import BasicInfo from '@/components/pages/staffs/basic_info/BasicInfo'
import DetailInfo from '@/components/pages/staffs/detail_info/DetailInfo'

export default {
  path: '/staffs',
  component: Template,
  children: [
    {
      path: '',
      name: 'staff-list',
      component: StaffList,
    },
    {
      path: ':id',
      name: 'basic-info',
      component: BasicInfo,
    },
    {
      path: ':id/profile',
      name: 'detail-info',
      component: DetailInfo,
    },
  ]
}