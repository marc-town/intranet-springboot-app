<template src="./sidebar.html"></template>
<script>
  import { mapState } from "vuex";
  export default {
    props: {
      source: String,
    },
    computed: {
      ...mapState('common', [
        "drawer", // this.commo.drawer を this.$store.state.common.drawer にマップ
      ]),
    },
    data: () => ({
      items: [
        { icon: 'mdi-contacts', text: 'Contacts', link: '/' },
        { icon: 'mdi-calendar-clock', text: 'Attendance', link: '/attendances' },
        { icon: 'mdi-content-copy', text: 'Duplicates', link: '/' },
        {
          icon: 'mdi-chevron-up',
          'icon-alt': 'mdi-chevron-down',
          text: 'Labels',
          model: true,
          children: [
            { icon: 'mdi-plus', text: 'Create label', link: '/' },
          ],
        },
        {
          icon: 'mdi-chevron-up',
          'icon-alt': 'mdi-chevron-down',
          text: 'More',
          model: false,
          children: [
            { text: 'Import', link: '/' },
            { text: 'Export', link: '/' },
            { text: 'Print', link: '/' },
            { text: 'Undo changes', link: '/' },
            { text: 'Other contacts', link: '/' },
          ],
        },
        { icon: 'mdi-message', text: 'Send feedback', link: '/' },
        { icon: 'mdi-cellphone-link', text: 'App downloads', link: '/' },
        { icon: 'mdi-keyboard', text: 'Go to the old version', link: '/' },
        { icon: 'mdi-help-circle', text: 'Help', link: '/' },
        { icon: 'mdi-settings', text: 'Settings', link: '/' },
      ],
    }),
    methods: {
    },
  }
</script>
<style scoped lang="scss">
  @import "./sidebar.scss";
</style>