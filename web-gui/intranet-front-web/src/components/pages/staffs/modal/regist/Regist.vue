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
        staff: {
          name: '',
          nameKana: '',
          loginId: '',
          emai: '',
          telephoneNumber: '',
          password: '',
        },
        defaultStaff: {
          name: '',
          nameKana: '',
          loginId: '',
          emai: '',
          telephoneNumber: '',
          password: '',
        },
        visibility: false,
        rules: {
          required: value => !!value || 'Required.',
          min: v => v.length >= 8 || 'Min 8 characters',
          emailMax: v => v.length <= 128 || 'Max 128 characters',
          emailMatch: v => /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(v) || 'email is invalid',
        },
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDialog',
      ]),
      onSignup: function() {
        alert(`called onSignup ${JSON.stringify(this.staff)}`)
        this.$axios.post('/staffs', this.staff)
          .then(res => {
            alert(JSON.stringify(res));
          })
          .catch(err => {
            alert(`output by regist: ${err}`);
          })
          .finaly(() => {
            this.setDialog(!this.dialog);
            this.close();
          })
      },
      close: function() {
        this.setDialog(!this.dialog);
        setTimeout(() => {
          this.staff = Object.assign({}, this.defaultStaff)
        }, 300)
      },
    }
  }
</script>
<style scoped lang="scss">
  @import "./regist.scss";
</style>