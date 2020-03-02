<template src="./staff_list.html"></template>
<script>
  import { mapActions } from "vuex";
  import RegistStaff from '@/components/pages/staffs/modal/regist/Regist'
  import DeleteStaff from '@/components/pages/staffs/modal/delete/Delete'
  import commonMethod from '@/mixins/common_methods'
  export default {
    components: { RegistStaff, DeleteStaff },
    computed: {},
    data () {
      return {
        staffs: [],
        search: '',
        headers: [
          { text: 'ID', align: 'start', value: 'staffId', },
          { text: 'Name', value: 'name' },
          { text: 'Department', value: 'department' },
          { text: 'Position', value: 'position' },
          { text: 'Grade', value: 'grade' },
          { text: 'Role', value: 'role' },
          { text: 'Actions', value: 'action', sortable: false },
        ],
        role: '',
      }
    },
    mixins: [ commonMethod ],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setStaffId',
        'setRegistDialog',
        'setDeleteDialog',
      ]),

      fetchData: function() {
        this.$axios.get('/staffs')
          .then(res => {
            this.staffs = res.data.staffs;
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
          })
      },

      initialize: function() {
        this.fetchData();
      },
      onDelete: function(id) {
        this.setStaffId(id);
        this.setDeleteDialog(true);
        // confirm('Are you sure you want to delete this item?')
      },
      getColor: function(value) {
        if (this.isAdmin(value)) return 'red';
        else if (this.isMiddle(value)) return 'primary';
        else if (this.isUser(value)) return '';
      }
    },
    created: function() {
      this.initialize()
    },
  }
</script>
<style scoped lang="scss">
  @import "./staff_list.scss";
</style>