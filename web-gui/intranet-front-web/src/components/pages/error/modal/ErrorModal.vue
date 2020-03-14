<template src="./error_modal.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {},
    data () {
      return {
      }
    },
    mixins: [],
    computed: {
      ...mapState('error', [
        "errorDialog",
        'status',
        'code',
        'message',
      ]),
    },
    watch: {},
    methods: {
      ...mapActions('error', [
        'setErrorDialog',
      ]),
      close: function() {
        this.setErrorDialog(false);
      }
    },
    created: function() {
    }
  }
</script>
<style scoped lang="scss">
  @import "./error_modal.scss";
</style>