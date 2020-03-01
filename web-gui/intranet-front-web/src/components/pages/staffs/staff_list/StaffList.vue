<template src="./staff_list.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  import RegistStaff from '@/components/pages/staffs/modal/regist/Regist'
  export default {
    components: { RegistStaff },
    props: {
      source: String,
    },
    computed: {
      ...mapState('staff', [
        'dialog',
      ])
    },
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
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDialog',
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
      onEdit: function(item) {
        this.editedIndex = this.staffs.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      onDelete: function(item) {
        const index = this.staffs.indexOf(item)
        confirm('Are you sure you want to delete this item?') && this.staffs.splice(index, 1)
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