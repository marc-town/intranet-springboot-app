<template src="./attendance.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
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
          { text: '休憩(h)', value: 'restTime', sortable: false },
          { text: '欠勤', value: 'absenceTypeId', align: 'center', sortable: false },
          { text: '欠勤理由', value: 'absenceReason', sortable: false },
          { text: '労働時間(h)', value: 'workingTime', align: 'center', sortable: false },
          { text: '夜間時間(h)', value: 'nightTime', align: 'center', sortable: false },
          { text: '営業費', value: 'operatingExpenses', sortable: false },
          { text: '区間', value: 'section', sortable: false },
          { text: '備考', value: 'remarks', sortable: false },
        ],
        days: [ '日', '月', '火', '水', '木', '金', '土' ],
        months: [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ],
        absenceTypes: ['欠勤', '代休', '有給', '忌引'],
        // 現在西暦
        currentYear: '',
        // 現在月
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
      nextYear: function() {
        return this.currentYear + 1;
      },
      preYear: function() {
        return this.currentYear - 1;
      },
    },
    watch: {
      // attendances: {
      //   handler: function(val) {
      //     console.log(`watch: ${JSON.stringify(val)}`);
      //   },
      //   deep: true
      // },
      '$route' (to) {
        this.currentYear = Number(to.query.year);
        this.currentMonth = Number(to.query.month);
      },
    },
    methods: {
      ...mapActions('error', [
        'setErrorDialog',
      ]),
      initialize: function() {
        this.loading = true;
        const date = new Date();
        this.currentYear = date.getFullYear();
        this.currentMonth = date.getMonth() + 1;
        this.startDate = new Date(this.currentYear, this.currentMonth - 1, 1);
        this.endDate = new Date(this.currentYear, this.currentMonth, 0);
        this.endDayCount = this.endDate.getDate();
        this.startDay = this.startDate.getDay();
        this.fetchData();
      },
      fetchData: function() {
        const yearMonth = `${ this.currentYear }${ this.zeroPadding(Number(this.currentMonth), 2) }`;
        const uri = `/attendances/${this.staffId}/?yearMonth=${ yearMonth }`;
        this.$axios.get(uri)
          .then(res => {
            this.attendances = res.data.attendances;
            if (!res.data.attendances.length) this.getInitialAttendance();
            this.loading = false;
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
            this.setErrorDialog(true);
          })
      },
      getInitialAttendance: function() {
        const defaultStartTime = this.getDefaultStartTime();
        const defaultEndTime = this.getDefaultEndTime();
        const defaultRestTime = this.getDefaultRestTime();
        for (let i = 0; i < this.endDayCount; i++) {
          const day = i + 1;
          const dayNum = (this.days.length + this.startDay + i) % this.days.length;
          const date = this.days[dayNum];
          const info = {
            day: day,
            date: date,
            startTime: defaultStartTime,
            endTime: defaultEndTime,
            restTime: defaultRestTime,
            absenceTypeId: 0,
            absenceReason: null,
            operatingExpenses: null,
            section: null,
            remarks: null
          };
          this.attendances.push(info);
        }
      },
      getDate: function(index) {
        const dayNumber = (this.days.length + this.startDay + index) % this.days.length;
        return this.days[dayNumber];
      },
      getDefaultStartTime: function() {
        return '09:00';
      },
      getDefaultEndTime: function() {
        return '17:30';
      },
      getDefaultRestTime: function() {
        return '1';
      },
      getWorkingTime: function(startTime, endTime, restTime) {
        if (startTime && endTime) {
          const startHourAndMinutes = startTime.split(':');
          const endHourAndMinutes = endTime.split(':');
          const startHour = Number(startHourAndMinutes[0]) * 60;
          const startMinutes = Number(startHourAndMinutes[1]);
          const endHour = Number(endHourAndMinutes[0]) * 60;
          const endMinutes = Number(endHourAndMinutes[1]);
  
          const start = startHour + startMinutes;
          let end = endHour + endMinutes;
          if (startHour > endHour) end += 24 * 60;
          const workingTime = (end - start) / 60 - restTime;
          return workingTime;
        }
        return 0;
      },
      onSave: function() {
        this.loading = true;
        const yearMonth = `${ this.currentYear }${ this.zeroPadding(Number(this.currentMonth), 2) }`;
        const uri = `/attendances/${this.staffId}/?yearMonth=${ yearMonth }`;
        this.$axios.put(uri, this.attendances)
          .then(() => {
            alert('保存しました');
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
            this.setErrorDialog(true);
          })
          .finally(() => {
            this.loading = false;
          })
      },
      onSubmit: function() {
        alert('on submit');
      },
      changeYearMonth: function(year, month) {
        this.currentYear = Number(year);
        this.currentMonth = Number(month);
        this.fetchData();
      },
      zeroPadding: function(num, len) {
        return ( Array(len).join('0') + num ).slice( -len );
      },
      isCurrentMonth: function(month) {
        return month == this.zeroPadding(Number(this.currentMonth), 2);
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