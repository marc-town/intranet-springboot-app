<template src="./staff_list.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  import RegistStaff from '@/components/pages/staffs/modal/regist/Regist'
  export default {
    components: { RegistStaff },
    props: {
      source: String,
    },
    computed: {
      ...mapState('staff', [
        'dialog',
      ])
    },
    data () {
      return {
        staffs: [],
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDialog',
      ]),
      fetchData: function() {
        this.$axios.get('/staffs')
          .then(res => {
            alert(JSON.stringify(res));
            this.staffs = res.data.staffs;
          })
          .catch(err => {
            alert(err);
          })
      },
    },
    created: function() {
      this.fetchData();
    },
  }
</script>
<style scoped lang="scss">
  @import "./staff_list.scss";
</style>