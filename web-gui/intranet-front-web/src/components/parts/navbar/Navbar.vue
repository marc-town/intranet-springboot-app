<template src="./navbar.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {},
    props: {
      source: String,
    },
    computed: {
      ...mapState('common', [
        "drawer",
      ]),
      ...mapState('auth', [
        "staffId",
      ]),
    },
    watch: {
    },
    data () {
      return {
        items: [
          { title: 'Profile', icon: 'mdi-account-circle' },
          { title: 'Logout', icon: 'mdi-logout-variant' },
        ],
      }
    },
    methods: {
      ...mapActions('common', [
        'setDraewer',
      ]),
      ...mapActions('auth', [
        'resetLoginInfo',
      ]),
      onLogout: function() {
        this.resetLoginInfo();
        sessionStorage.removeItem('gsol-intranet-app');
        this.$router.push('login');
      },
    },
    created: function() {
    }
  }
</script>
<style scoped lang="scss">
  @import "./navbar.scss";
</style>