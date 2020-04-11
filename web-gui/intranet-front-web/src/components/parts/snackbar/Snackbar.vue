<template src="./snackbar.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {},
    data () {
      return {
      }
    },
    computed: {
      ...mapState('snackbar', [
        'color',
        'message',
        'timeout',
        'y',
      ]),
      snackbar: {
        get () { return this.$store.state.snackbar.snackbar },
        set (val) { this.close(val) },
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('snackbar', [
        'resetSnackbar',
      ]),
      close: function() {
        this.resetSnackbar();
      },
    },
    beforeDestroy: function() {
    },
    destroyed: function() {
    }
  }
</script>
<style scoped lang="scss">
</style>