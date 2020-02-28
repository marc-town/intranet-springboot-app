<template src="./index.html"></template>
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
      ...mapState('auth', [
        "token",
        "role"
      ]),
    },
    watch: {},
    methods: {
      ...mapActions('common', [
        'setDraewer',
      ]),
    },
    created: function() {
      this.setDraewer(false);
      
    },
    beforeDestroy: function() {
      this.setDraewer(true);
    }
  }
</script>
<style >
  @import "./index.css";
</style>