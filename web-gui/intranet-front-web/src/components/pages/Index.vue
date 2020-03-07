<template src="./index.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {},
    data () {
      return {
        colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
        ],
        items: [
          'First',
          'Second',
          'Third',
          'Fourth',
          'Fifth',
        ],
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
    // 以下、ライフサイクル系処理
    beforeCreate: function() {
    },
    created: function() {
    },
    beforeMount: function() {
    },
    mounted: function() {
    },
    beforeUpdate: function() {
    },
    updated: function() {
    },
    beforeDestroy: function() {
    },
    destroyed: function() {

    }
  }
</script>
<style scoped lang="scss">
  @import "./index.scss";
</style>