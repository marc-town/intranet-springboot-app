<template src="./regist.html"></template>
<script>
  import { mapActions } from "vuex";
  export default {
    components: {},
    props: {
      source: String,
    },
    computed: {
      registDialog: {
        get () { return this.$store.state.staff.registDialog },
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
          role: 3,
        },
        roles: [
          { 'id': 1, 'name': 'ADMIN'},
          { 'id': 2, 'name': 'MIDDLE'},
          { 'id': 3, 'name': 'USER'},
        ],
        visibility: false,
        // rules: {
        //   required: value => !!value || 'Required.',
        //   min: v => v.length >= 8 || 'Min 8 characters',
        //   emailMax: v => v.length <= 128 || 'Max 128 characters',
        //   emailMatch: v => /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(v) || 'email is invalid',
        // },
      }
    },
    mixins: [],
    watch: {
      registDialog: function(val) {
        if (val) this.staff.password = this.generatePassword();
      },
    },
    methods: {
      ...mapActions('staff', [
        'setRegistDialog',
      ]),
      initialize: function() {
        this.staff.password = this.generatePassword();
      },
      onSignup: function() {
        this.$axios.post('/staffs', this.staff)
          .then(res => {
            alert(JSON.stringify(res));
          })
          .catch(err => {
            alert(`output by regist: ${err}`);
          })
          .finally(() => {
            this.close();
            this.$emit('from-child')
          })
      },
      generatePassword: function() {
        const letters = 'abcdefghijklmnopqrstuvwxyz';
        var numbers = '0123456789';
        var string  = letters + letters.toUpperCase() + numbers + '@!?#$';

        var len = 13;
        var password = '';

        for (var i = 0; i < len; i++) {
          password += string.charAt(Math.floor(Math.random() * string.length));
        }

        return password;
      },
      setDefault: function() {
          this.staff.name = '';
          this.staff.nameKana = '';
          this.staff.loginId = '';
          this.staff.emai = '';
          this.staff.telephoneNumber = '';
          this.staff.password = '';
          this.staff.role = 3;
          this.visibility = false;
      },
      close: function() {
        this.setRegistDialog(!this.registDialog);
        this.setDefault();
      },
      created: function() {
      },
    }
  }
</script>
<style scoped lang="scss">
  @import "./regist.scss";
</style>