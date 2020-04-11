<template src="./basic_info.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  import commonMethod from '@/mixins/common_methods'
  export default {
    components: {},
    props: {
      source: String,
    },
    data () {
      return {
        staffId: 0,
        editMode: false,
        displayInfo: {},
        form: {
          name: '',
          nameKana: '',
          enteredDate: '',
          birthday: '',
          telephoneNumber: '',
          departmentId: 0,
          positionId: 0,
          gradeId: 0,
          role: 0,
        },
        enteredModal: false,
        birthdayModal: false,
        departments: [],
        positions: [],
        grades: [],
        roles: [],
      }
    },
    computed: {
      ...mapState('auth', [
        "role",
      ]),
      affiliationYears: function() {
        return this.calculateAffiliationYears();
      }
    },
    mixins: [ commonMethod ],
    watch: {
      'displayInfo.name': function(val) {
        this.form.name = val;
      },
      'displayInfo.nameKana': function(val) {
        this.form.nameKana = val;
      },
      'displayInfo.enteredDate': function(val) {
        this.form.enteredDate = val;
      },
      'displayInfo.birthday': function(val) {
        this.form.birthday = val;
      },
      'displayInfo.telephoneNumber': function(val) {
        this.form.telephoneNumber = val;
      },
      editMode: function(val) {
        if (val) this.initSelectItems();
      }
    },
    methods: {
      ...mapActions('snackbar', [
        'setSnackbar',
      ]),
      init: function() {
        this.staffId = this.$route.params['id'];
        this.fetchData();
        if (this.isAdmin(this.role)) {
          this.fetchDepartments();
          this.fetchPositions();
          this.fetchGrades();
          this.fetchRoles();
        }
      },
      fetchData: function() {
        const uri = `/staffs/${ this.staffId }/basic_info`;
        this.$axios.get(uri)
          .then(res => {
            this.displayInfo = res.data.basicInfo;
          })
          .catch(err => {
            alert(`output by basicInfo: ${JSON.stringify(err)}`);
          })
      },
      fetchDepartments: function() {
        this.$axios.get('/departments')
          .then(res => {
            this.departments = res.data.departments;
          })
          .catch(err => {
            console.error(`output by basicInfo: ${JSON.stringify(err)}`);
          })
      },
      fetchPositions: function() {
        this.$axios.get('/positions')
          .then(res => {
            this.positions = res.data.positions;
          })
          .catch(err => {
            console.error(`output by basicInfo: ${JSON.stringify(err)}`);
          })
      },
      fetchGrades: function() {
        this.$axios.get('/grades')
          .then(res => {
            this.grades = res.data.grades;
          })
          .catch(err => {
            console.error(`output by basicInfo: ${JSON.stringify(err)}`);
          })
      },
      fetchRoles: function() {
        this.$axios.get('/roles')
          .then(res => {
            this.roles = res.data.roles;
          })
          .catch(err => {
            console.error(`output by basicInfo: ${JSON.stringify(err)}`);
          })
      },
      onSave: function() {
        const uri = `/staffs/${ this.staffId }/basic_info`;
        this.$axios.put(uri, this.form)
          .then(res => {
            console.log(res);
            const options = {
              snackbar: true,
              color: 'info',
              message: '保存しました',
              timeout: 5000,
            };
            this.setSnackbar(options);
            this.fetchData();
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
      },
      calculateAffiliationYears: function() {
        const today = new Date();
        const enteredDate = new Date(this.displayInfo.enteredDate)
        const diffMonth = today.getMonth() - enteredDate.getMonth();
        const diffYear = today.getFullYear() - enteredDate.getFullYear();
        return `${ diffYear }.${ diffMonth }`;
      },
      orgFloor: function(value, base) {
        return Math.floor(value * base) / base;
      },
      initSelectItems: function() {
        for (let row of this.departments) {
          if (row.name === this.displayInfo.department) this.form.departmentId = row.id;
        }
        for (let row of this.positions) {
          if (row.name === this.displayInfo.position) this.form.positionId = row.id;
        }
        for (let row of this.grades) {
          if (row.name === this.displayInfo.grade) this.form.gradeId = row.id;
        }
        for (let row of this.roles) {
          if (row.name === this.displayInfo.role) this.form.role = row.id;
        }
      },
    },
    // ライフサイクル系処理
    beforeCreate: function() {
    },
    created: function() {
      this.init();
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
  @import "./basic_info.scss";
</style>