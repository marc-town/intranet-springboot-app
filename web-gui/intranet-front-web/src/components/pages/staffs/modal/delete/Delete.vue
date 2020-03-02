<template src="./delete.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {},
    computed: {
      ...mapState('staff', [
        'staffId',
        'deleteDialog',
      ])
    },
    data () {
      return {
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDeleteDialog',
      ]),
      onDelete: function() {
        const uri = `/staffs/${this.staffId}`;
        this.$axios.delete(uri)
          .then(res => {
            alert(JSON.stringify(res));
          })
          .catch(err => {
            alert(`output by delete modal: ${err}`);
          })
          .finally(() => {
            this.close();
          })
      },
      close: function() {
        this.setDeleteDialog(!this.deleteDialog);
      },
    }
  }
</script>
<style scoped lang="scss">
  @import "./delete.scss";
</style>