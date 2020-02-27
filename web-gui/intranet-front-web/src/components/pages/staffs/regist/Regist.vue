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
        password: '',
        visibility: false,
        rules: {
          required: value => !!value || 'Required.',
          min: v => v.length >= 8 || 'Min 8 characters',
          emailMatch: () => ('The email and password you entered don\'t match'),
        },
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDialog', // this.setDialog() を this.$sotre.dispatch("setDialog") にマップ
      ]),
      onCancel: function() {
        console.log('入力値破棄');
      },
    }
  }
</script>
<style >
  @import "./regist.scss";
</style>