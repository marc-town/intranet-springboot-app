<template src="./signin.html"></template>
<script>
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
    }
  },
  mixins: [],
  computed: {
  },
  watch: {},
  methods: {
    onSignin: function() {
      // バリデーションが通った場合
      // if (this.$refs.loginForm.validate()) {
      //   // ぐるぐる表示にしてボタンを二度押しできなくする
      //   this.loading = true
      //   // APIでログイン認証を行う
      //   this.$axios.post("/signin", this.auth).then(res => {
      //     if (res.data.result) {
      //       // ログイン情報を store に保存
      //       this.$store.dispatch("setLoginInfo", res.data)
      //       // 元の画面に戻る
      //       this.$router.push({path: "backuri" in this.$route.query && this.$route.query.backuri.match(/^\//) ? this.$route.query.backuri : '/'})
      //     } else {
      //       this.loading = false
      //       alert(Object.values(res.data.errors).join("\n"))
      //     }
      //   }).catch(err => {
      //     alert(err)
      //     this.loading = false
      //   })
      // }
      alert('called onsignin');
    }
  }
}
</script>
<style scoped lang="scss">
  @import "./signin.scss";
</style>