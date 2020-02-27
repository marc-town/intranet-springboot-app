<template src="./staff_list.html"></template>
<script>
  import { mapActions } from "vuex";
  import RegistStaff from '@/components/pages/staffs/regist/Regist'
  export default {
    components: { RegistStaff },
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
  @import "./staff_list.css";
</style>