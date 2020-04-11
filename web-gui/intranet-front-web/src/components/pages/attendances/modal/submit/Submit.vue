<template src="./submit.html"></template>
<script>
  import { VTextField } from 'vuetify/lib';
  import { mapState, mapActions } from "vuex";
  export default {
    components: { VTextField },
    computed: {
      ...mapState('attendance', [
        'submitAttendance',
        'staffId',
        'currentYear',
        'currentMonth',
        'totalWorkingTime',
        'totalNightTime',
        'totalOverTime'
      ])
    },
    data () {
      return {
        customerResidentTime: null,
      }
    },
    mixins: [],
    watch: {
    },
    methods: {
      ...mapActions('attendance', [
        'resetAttendance',
      ]),
      ...mapActions('snackbar', [
        'setSnackbar',
      ]),
      onSubmit: function() {
        const yearMonth = `${ this.currentYear }${ this.zeroPadding(Number(this.currentMonth), 2) }`;
        const uri = `/attendances/${this.staffId}/submit?yearMonth=${ yearMonth }`;
        const body = {
          customerResidentTime: this.customerResidentTime,
          totalWorkingTime: this.totalWorkingTime,
          totalNightTime: this.totalNightTime,
          totalOverTime: this.totalOverTime,
        };
        this.$axios.post(uri, body)
          .then(() => {
            const options = {
              snackbar: true,
              color: 'info',
              message: `${this.currentYear}年${this.currentMonth}月分の勤怠情報を提出しました`,
              timeout: 5000,
            }
            this.setSnackbar(options);
          })
          .catch(err => {
            const options = {
              snackbar: true,
              color: 'error',
              message: JSON.stringify(err),
              timeout: 5000,
            }
            this.setSnackbar(options);
          })
          .finally(() => {
            this.close();
          })
      },
      close: function() {
        this.customerResidentTime = null;
        this.resetAttendance();
      },
      zeroPadding: function(num, len) {
        return ( Array(len).join('0') + num ).slice( -len );
      },
    },
    // 以下、ライフサイクル系処理
    beforeCreate: function() {
    },
    created: function() {
      },
    beforeMount: function() {
    },
    mounted: function() {
    },
    beforeUpdate: function() {
    },
    updated: function() {
    },
    beforeDestroy: function() {
    },
    destroyed: function() {
    }
  }
</script>
<style scoped lang="scss">
  @import "./submit.scss";
</style>