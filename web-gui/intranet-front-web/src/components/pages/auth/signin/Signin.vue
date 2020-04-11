<template src="./signin.html"></template>
<script>
  import { mapActions } from "vuex";
  export default {
    components: {},
    props: {
      source: String,
    },
    data () {
      return {
        loading: false,
        rules: {
          required: value => !!value || 'Required.',
          min: v => v.length >= 8 || 'Min 8 characters',
          emailMatch: () => ('The email and password you entered don\'t match'),
        },
        auth: {
          loginId: '',
          password: ''
        },
        error: false,
        errorMessage: ''
      }
    },
    mixins: [],
    computed: {
    },
    watch: {},
    methods: {
      ...mapActions('auth', [
        'setLoginInfo',
      ]),
      onSignin: function() {
        // バリデーションが通った場合
        if (this.$refs.loginForm.validate()) {
          // ぐるぐる表示にしてボタンを二度押しできなくする
          this.loading = true
          // APIでログイン認証を行う
          this.$axios.post("/auth/signin", this.auth).then(res => {
            if (res.data.token) {
              // ログイン情報を store に保存
              const auth = {
                token: res.data.token,
                staffId: res.data.staffId,
                loginId: res.data.loginId,
                role: res.data.role[0],
                expire: res.data.expire
              };
              this.setLoginInfo(auth);
              // 元の画面に戻る
              this.$router.push({path: "backuri" in this.$route.query ? this.$route.query.backuri : '/'})
            } else {
              this.loading = false
              alert(Object.values(res.data.errors).join("\n"))
            }
          }).catch(err => {
            if (err.response.status === 401) {
              this.error = true;
              this.errorMessage = 'ログインIDまたはパスワードが間違っています';
            }
            this.loading = false
          })
        }
      }
    }
  }
</script>
<style scoped lang="scss">
  @import "./signin.scss";
</style>