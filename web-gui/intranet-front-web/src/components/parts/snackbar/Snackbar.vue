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
        'snackbar',
        'color',
        'message',
        'timeout',
        'y',
      ]),
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