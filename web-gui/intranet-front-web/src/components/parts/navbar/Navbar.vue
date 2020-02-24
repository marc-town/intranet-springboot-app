<template src="./navbar.html"></template>
<script>
  import { mapActions } from "vuex";
  export default {
    components: {},
    props: {
      source: String,
    },
    computed: {
      drawer: {
        get () { return this.$store.state.common.drawer },
        set (val) { this.setStoreDraewer(val) }
      }
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