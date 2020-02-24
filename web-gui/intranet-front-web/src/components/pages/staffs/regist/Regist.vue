<template src="./regist.html"></template>
<script>
  import { mapActions } from "vuex";
  export default {
    components: {},
    props: {
      source: String,
    },
    computed: {
      dialog: {
        get () { return this.$store.state.staff.dialog },
        set (val) { this.setDialog(val) }
      }
    },
    data () {
      return {
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDialog', // this.setDialog() を this.$sotre.dispatch("setDialog") にマップ
      ]),
    }
  }
</script>
<style >
  @import "./regist.scss";
</style>