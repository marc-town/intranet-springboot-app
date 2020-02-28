<template src="./index.html"></template>
<script>
  import Navbar from '@/components/parts/navbar/Navbar'
  import { mapState } from "vuex";
  export default {
    components: { Navbar },
    data () {
      return {
      }
    },
    mixins: [],
    computed: {
      ...mapState('auth', [
        "token",
      ]),
    },
    watch: {},
    methods: {
    },
    created: function() {
    }
  }
</script>
<style >
  @import "./index.css";
</style>