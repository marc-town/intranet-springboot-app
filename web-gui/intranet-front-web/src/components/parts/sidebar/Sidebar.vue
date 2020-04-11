<template src="./sidebar.html"></template>
<script>
  import { mapState } from "vuex";
  import commonMethod from '@/mixins/common_methods'
  export default {
    props: {
      source: String,
    },
    computed: {
      ...mapState('common', [
        "drawer", // this.commo.drawer を this.$store.state.common.drawer にマップ
      ]),
      ...mapState('auth', [
        "role",
      ]),
      loginStaffId: {
        get () { return this.$store.state.auth.staffId },
        // set (val) { this.setDialog(val) }
      }
    },
    watch: {
    },
    mixins: [ commonMethod ],
    data: () => ({
      items: [
        { icon: 'mdi-calendar-clock', text: '月報', link: '/attendances' },
        { icon: 'mdi-file-document-box', text: '技術ブログ', link: '/tech-blog' },
        { icon: 'mdi-calendar-month', text: 'スケジュール管理', link: '/schedules' },
        { icon: 'mdi-account-card-details', text: '基本情報', link: '/' },
      ],
      adminItems: [
        { icon: 'mdi-settings', text: '社員管理', link: '/staffs' },
      ],
    }),
    methods: {
    },
    // 以下、ライフサイクル系処理
    beforeCreate: function() {
    },
    created: function() {
    },
    beforeMount: function() {
    },
    mounted: function() {
      this.items[3].link = `/staffs/${ this.loginStaffId }`;
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
  @import "./sidebar.scss";
</style>