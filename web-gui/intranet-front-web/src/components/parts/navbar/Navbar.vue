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
    data: () => ({
      items: [
        { title: 'Profile', icon: 'mdi-account-circle', link: '' },
        { title: 'Logout', icon: 'mdi-logout-variant', link: '/login' },
      ],
    }),
    methods: {
      ...mapActions('common', [
        'setDraewer',
      ]),
    },
    created: function() {
      this.items[0].link = `/staffs/${this.staffId}`;
    }
  }
</script>
<style scoped lang="scss">
  @import "./navbar.scss";
</style>