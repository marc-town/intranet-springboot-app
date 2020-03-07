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
      onDelete: function() {
        const uri = `/staffs/${this.selectedStaffId}`;
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