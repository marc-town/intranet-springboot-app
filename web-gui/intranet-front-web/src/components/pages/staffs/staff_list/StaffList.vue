<template src="./staff_list.html"></template>
<script>
import RegistStaff from '@/components/pages/staffs/regist/Regist'
export default {
  components: { RegistStaff },
  props: {
    source: String,
  },
  data () {
    return {
      dialog: false,
    }
  },
  mixins: [],
  computed: {
  },
  watch: {},
  methods: {
  }
}
</script>
<style >
  @import "./staff_list.css";
</style>