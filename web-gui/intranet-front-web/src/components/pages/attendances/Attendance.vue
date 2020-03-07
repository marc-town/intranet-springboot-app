<template src="./attendance.html"></template>
<script>
  import { mapState } from "vuex";
  export default {
    components: {},
    data () {
      return {
        loading: false,
        edited: [],
        attendances: [],
        headers: [
          { text: '日付', value: 'day', sortable: false, width: '56' },
          { text: '曜日', value: 'date', sortable: false, width: '56' },
          { text: '開始', value: 'startTime', sortable: false },
          { text: '終了', value: 'endTime', sortable: false },
          { text: '休憩', value: 'restTime', sortable: false },
          { text: '欠勤', value: 'absenceTypeId', align: 'center', sortable: false },
          { text: '欠勤理由', value: 'absenceReason', sortable: false },
          { text: '労働時間', value: 'workingTime', align: 'center', sortable: false },
          { text: '夜間時間', value: 'nightTime', align: 'center', sortable: false },
          { text: '営業費', value: 'operatingExpenses', sortable: false },
          { text: '区間', value: 'section', sortable: false },
          { text: '備考', value: 'remarks', sortable: false },
        ],
        days: [ '日', '月', '火', '水', '木', '金', '土' ],
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
      initialize: function() {
        this.loading = true;
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
        // const yearMonth = `${ this.currentYear }${ this.zeroPadding(Number(this.currentMonth), 2) }`;
        // const uri = `/attendances/?yearMonth=${ yearMonth }`;
        const uri = '/attendances/?yearMonth=202002'
        const body = { 'staffId': this.staffId };
        this.$axios.post(uri, body)
          .then(res => {
            this.attendances = res.data.attendances;
            this.loading = false;
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
          })
      },
      getDate: function(index) {
        const dayNumber = (this.days.length + this.startDay + index) % this.days.length;
        return this.days[dayNumber];
      },
      zeroPadding: function(num, len) {
        return ( Array(len).join('0') + num ).slice( -len );
      },
    },
    created: function() {
      this.initialize();
    }
  }
</script>
<style >
  @import "./attendance.css";
</style>