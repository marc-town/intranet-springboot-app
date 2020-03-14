<template src="./delete.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  export default {
    components: {},
    computed: {
      ...mapState('staff', [
        'selectedStaffId',
        'selectedStaffName',
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
      ...mapActions('snackbar', [
        'setSnackbar',
      ]),
      onDelete: function() {
        const uri = `/staffs/${this.selectedStaffId}`;
        this.$axios.delete(uri)
          .then(() => {
            const options = {
              snackbar: true,
              color: 'info',
              message: `${ this.selectedStaffName }を抹消しました`,
              timeout: 5000,
            }
            this.setSnackbar(options);
          })
          .catch(err => {
            const options = {
              snackbar: true,
              color: 'error',
              message: JSON.stringify(err),
              timeout: 5000,
            }
            this.setSnackbar(options);
          })
          .finally(() => {
            this.close();
            this.$emit('from-child')
          })
      },
      close: function() {
        this.setDeleteDialog(false);
      },
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
  @import "./delete.scss";
</style>