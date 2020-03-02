<template src="./attendance.html"></template>
<script>
  export default {
    components: {},
    data () {
      return {
        attendances: [],
        headers: [
            { text: '日付', align: 'start', value: 'day', },
            { text: 'Name', value: 'name' },
            { text: 'Department', value: 'department' },
            { text: 'Position', value: 'position' },
            { text: 'Grade', value: 'grade' },
            { text: 'Role', value: 'role' },
            { text: 'Actions', value: 'action', sortable: false },
          ],
          dayOfWeek: [ 'Sun', 'Mon', 'TUE', 'WED', 'THU', 'FRI', 'SAT' ],
      }
    },
    mixins: [],
    computed: {
    },
    watch: {},
    methods: {
    }
  }
</script>
<style >
  @import "./attendance.css";
</style>