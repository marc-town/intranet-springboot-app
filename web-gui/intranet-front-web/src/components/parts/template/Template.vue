<template src="./template.html"></template>
<script>
  import Navbar from '@/components/parts/navbar/Navbar'
  import Sidebar from '@/components/parts/sidebar/Sidebar'
  export default {
    components: { Navbar, Sidebar },
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