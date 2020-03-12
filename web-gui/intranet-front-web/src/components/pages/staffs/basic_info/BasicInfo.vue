<template src="./basic_info.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {
      ...mapState('staff', [
        'selectedStaffId',
        'selectedStaffName',
        'deleteDialog',
      ])
    },
    data () {
      return {
        minValue: 10,
      }
    },
    computed: {
      minValueRules() {
        return [ v => {
          if (!this.minValue) return true
          return v > this.minValue || `${this.minValue}より大きくないと駄目です`
        }]
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('snackbar', [
        'setSnackbar',
      ]),
    }
  }
</script>
<style scoped lang="scss">
  @import "./basic_info.scss";
</style>