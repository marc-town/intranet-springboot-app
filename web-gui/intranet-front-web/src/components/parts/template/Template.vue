<template src="./template.html"></template>
<script>
  import Navbar from '@/components/parts/navbar/Navbar'
  import Sidebar from '@/components/parts/sidebar/Sidebar'
  import Snackbar from '@/components/parts/snackbar/Snackbar'
  import ErrorModal from '@/components/pages/error/modal/ErrorModal'
  export default {
    components: { Navbar, Sidebar, Snackbar, ErrorModal },
    props: {
      source: String,
    },
    data: () => ({
      dialog: false,
      drawer: true,
    }),
  }
</script>
<style scoped lang="scss">
  @import "./template.scss";
</style>