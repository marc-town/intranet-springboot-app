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
        { icon: 'mdi-contacts', text: 'Contacts', path: '/' },
        { icon: 'mdi-history', text: 'Attendance', path: '/' },
        { icon: 'mdi-content-copy', text: 'Duplicates', path: '/' },
        {
          icon: 'mdi-chevron-up',
          'icon-alt': 'mdi-chevron-down',
          text: 'Labels',
          model: true,
          path: '/',
          children: [
            { icon: 'mdi-plus', text: 'Create label', path: '/' },
          ],
        },
        {
          icon: 'mdi-chevron-up',
          'icon-alt': 'mdi-chevron-down',
          text: 'More',
          model: false,
          path: '/',
          children: [
            { text: 'Import', path: '/' },
            { text: 'Export', path: '/' },
            { text: 'Print', path: '/' },
            { text: 'Undo changes', path: '/' },
            { text: 'Other contacts', path: '/' },
          ],
        },
        { icon: 'mdi-settings', text: 'Settings', path: '/' },
        { icon: 'mdi-message', text: 'Send feedback', path: '/' },
        { icon: 'mdi-help-circle', text: 'Help', path: '/' },
        { icon: 'mdi-cellphone-link', text: 'App downloads', path: '/' },
        { icon: 'mdi-keyboard', text: 'Go to the old version', path: '/' },
      ],
    }),
    methods: {
    },
  }
</script>
<style scoped lang="scss">
  @import "./sidebar.scss";
</style>