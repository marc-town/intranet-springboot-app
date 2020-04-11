<template src="./staff_list.html"></template>
<script>
  import { mapActions } from "vuex";
  import RegistStaff from '@/components/pages/staffs/modal/regist/Regist'
  import DeleteStaff from '@/components/pages/staffs/modal/delete/Delete'
  import commonMethod from '@/mixins/common_methods'
  export default {
    components: { RegistStaff, DeleteStaff },
    computed: {
      authStaffId: {
        get () { return this.$store.state.auth.staffId },
      }
    },
    data () {
      return {
        loading: false,
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
        role: {
          'ROLE_ADMIN': 'ADMIN',
          'ROLE_MIDDLE': 'MIDDLE',
          'ROLE_USER': 'USER',
        },
      }
    },
    mixins: [ commonMethod ],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setSelectedStaffId',
        'setSelectedStaffName',
        'setRegistDialog',
        'setDeleteDialog',
      ]),
      initialize: function() {
        this.loading = true;
        this.fetchData();
      },
      reload() {
        this.$router.go({path: this.$router.currentRoute.path, force: true});
      },
      fetchData: function() {
        this.$axios.get('/staffs')
          .then(res => {
            this.staffs = res.data.staffs;
            this.loading = false;
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
          })
      },
      onDelete: function(id, name) {
        this.setSelectedStaffId(id);
        this.setSelectedStaffName(name);
        this.setDeleteDialog(true);
      },
      doneRegist: function() {
        this.fetchData();
      },
      doneDelete: function() {
        this.fetchData();
      },
      isOwnAccount: function(id) {
        return id === this.authStaffId;
      },
    },
    created: function() {
      this.initialize()
    },
  }
</script>
<style scoped lang="scss">
  @import "./staff_list.scss";
</style>