<template src="./attendance.html"></template>
<script>
  import { mapState } from "vuex";
  export default {
    components: {},
    data () {
      return {
        edited: [],
        attendances: [],
        headers: [
          { text: '日付', value: 'day', },
          { text: '曜日', value: 'date' },
          { text: '開始', value: 'startTime' },
          { text: '終了', value: 'endTime' },
          { text: '休憩', value: 'restTime' },
          { text: '欠勤', value: 'absenceTypeId' },
          { text: '欠勤理由', value: 'absenceReason' },
          { text: '労働時間', value: 'workingTime' },
          { text: '夜間時間', value: 'nightTime' },
          { text: '営業費', value: 'operatingExpenses' },
          { text: '区間', value: 'section' },
          { text: '備考', value: 'remarks' },
        ],
        days: [ 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN' ],
        // 西暦
        currentYear: '',
        // 対象月
        currentMonth: '',
        // 月初日付
        startDate: '',
        // 月末日付
        endDate: '',
        // 月の末尾
        endDayCount: '',
        // 月の最初の曜日
        startDay: '',
      }
    },
    mixins: [],
    computed: {
      ...mapState('auth', [
        "staffId",
      ]),
    },
    watch: {},
    methods: {
      init: function() {
        const date = new Date()
        this.currentYear = date.getFullYear()
        this.currentMonth = date.getMonth() + 1
        this.startDate = new Date(this.currentYear, this.currentMonth - 1, 1)
        this.endDate = new Date(this.currentYear, this.currentMonth, 0)
        this.endDayCount = this.endDate.getDate()
        this.startDay = this.startDate.getDay()
        this.fetchData();
      },
      fetchData: function() {
        // const yearMonth = `${ this.currentYear }${ this.currentMonth }`;
        // const uri = `/attendances/?${ yearMonth }`;
        const uri = '/attendances/?yearMonth=202002'
        const body = { 'staffId': this.staffId };
        alert(uri);
        this.$axios.post(uri, body)
          .then(res => {
            alert(`!!! attendances ${JSON.stringify(res)}`);
            this.attendances = res.data.attendances;
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
          })
      },
    },
    created: function() {
      this.init();
    }
  }
</script>
<style >
  @import "./attendance.css";
</style>