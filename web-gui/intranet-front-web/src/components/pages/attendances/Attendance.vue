<template src="./attendance.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  import JapaneseHolidays from '@/plugins/japanese-holidays'
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
          { text: '出社時間', value: 'startTime', sortable: false },
          { text: '退勤時間', value: 'endTime', sortable: false },
          { text: '休憩', value: 'restTime', sortable: false },
          { text: '欠勤', value: 'absenceTypeId', align: 'center', sortable: false, width: '50' },
          { text: '欠勤理由', value: 'absenceReason', sortable: false },
          { text: '労働時間', value: 'workingTime', align: 'center', sortable: false, width: '60' },
          { text: '夜間時間', value: 'nightTime', align: 'center', sortable: false, width: '60' },
          { text: '営業費', value: 'operatingExpenses', sortable: false },
          { text: '区間', value: 'section', sortable: false },
          { text: '備考', value: 'remarks', sortable: false },
        ],
        days: [ '日', '月', '火', '水', '木', '金', '土' ],
        months: [ '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12' ],
        absenceTypes: [
          { id: null, name: ''},
          { id: 1, name: '欠勤' },
          { id: 2, name: '代休' },
          { id: 3, name: '有給' },
          { id: 4, name: '忌引' },
        ],
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
          const info = {
            day: day,
            startTime: this.isHoliday(day) ? null : defaultStartTime,
            endTime: this.isHoliday(day) ? null : defaultEndTime,
            restTime: this.isHoliday(day) ? null : defaultRestTime,
            absenceTypeId: null,
            absenceReason: null,
            operatingExpenses: null,
            section: null,
            remarks: null
          };
          this.attendances.push(info);
        }
      },
      getDate: function(day) {
        const currentDay = `${this.currentYear}/${this.currentMonth}/${this.zeroPadding(Number(day), 2)}`;
        const date = new Date(currentDay);
        const weekday = date.getDay();
        return this.days[weekday];
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
          const start = this.calculateMinutesTime(startTime);
          let end = this.calculateMinutesTime(endTime);
          if (start > end) end += 24 * 60;
          const workingTime = (end - start) / 60 - restTime;
          return workingTime;
        }
        return 0;
      },
      getNightTime: function(startTime, endTime, restTime) {
        // 出勤時刻 < 29:00 かつ 退勤時刻 > 22:00
        // 退勤時間または29:00で値の小さい方 - 出勤時間または22:00で値の小さい方
        if (startTime && endTime) {
          const start = this.calculateMinutesTime(startTime);
          const end = this.calculateMinutesTime(endTime);
          const nightHead = this.calculateMinutesTime('22:00');
          const nightTail = this.calculateMinutesTime('29:00');
          if (start < nightTail && end > nightHead) {
            return (Math.min(end, nightTail) - Math.min(start, nightHead)) / 60 - restTime;
          }
        }
        return 0;
      },
      calculateMinutesTime: function(time) {
        const hourAndMinutes = time.split(':');
        const hour = Number(hourAndMinutes[0]) * 60;
        const minutes = Number(hourAndMinutes[1]);
        return hour + minutes;
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
      isHoliday: function(day) {
        if (!day) return false;
        const currentDay = `${this.currentYear}/${this.currentMonth}/${this.zeroPadding(Number(day), 2)}`;
        const defaultHolidays = [ '土', '日' ];
        const date = new Date(currentDay);
        const weekday = date.getDay();
        const holidayName = JapaneseHolidays.getHolidayName(date);
        if (defaultHolidays.includes(this.days[weekday])) {
          return true;
        } else if (holidayName) {
          return true;
        } else {
          return false;
        }
      },
      getDayColor: function(day) {
        if (!day) return false;
        const currentDay = `${this.currentYear}/${this.currentMonth}/${this.zeroPadding(Number(day), 2)}`;
        const date = new Date(currentDay);
        const weekday = date.getDay();
        if (weekday === 6) return 'blue';
        else if (this.isHoliday) return 'red';
        else return '';
      },
    },
    created: function() {
      this.initialize();
      this.isHoliday();
    }
  }
</script>
<style scoped lang="scss">
  @import "./attendance.scss";
</style>