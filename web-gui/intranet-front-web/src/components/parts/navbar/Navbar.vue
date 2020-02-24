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
        "drawer", // this.commo.drawer を this.$store.state.common.drawer にマップ
      ]),
    },
    watch: {
    },
    data: () => ({
    }),
    methods: {
      ...mapActions('common', [
        'setDraewer', // this.setDraew() を this.$sotre.dispatch("setDraew") にマップ
      ]),
    },
  }
</script>
<style scoped lang="scss">
  @import "./navbar.scss";
</style>