<template src="./staff_list.html"></template>
<script>
  import { mapState, mapActions } from "vuex";
  import RegistStaff from '@/components/pages/staffs/modal/regist/Regist'
  export default {
    components: { RegistStaff },
    props: {
      source: String,
    },
    computed: {
      ...mapState('staff', [
        'dialog',
      ])
    },
    data () {
      return {
        staffs: [],
        search: '',
        headers: [
          {
            text: 'Dessert (100g serving)',
            align: 'start',
            sortable: false,
            value: 'name',
          },
          { text: 'Calories', value: 'calories' },
          { text: 'Fat (g)', value: 'fat' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Actions', value: 'action', sortable: false },
        ],
        desserts: [],
        editedIndex: -1,
        editedItem: {
          name: '',
          calories: 0,
          fat: 0,
          carbs: 0,
          protein: 0,
        },
        defaultItem: {
          name: '',
          calories: 0,
          fat: 0,
          carbs: 0,
          protein: 0,
        },
      }
    },
    mixins: [],
    watch: {},
    methods: {
      ...mapActions('staff', [
        'setDialog',
      ]),

      fetchData: function() {
        this.$axios.get('/staffs')
          .then(res => {
            this.staffs = res.data.staffs;
            alert(JSON.stringify(this.staffs));
          })
          .catch(err => {
            alert(`output by staffs: ${err}`);
          })
      },

      initialize: function() {
        this.fetchData();
        this.desserts = [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
          },
          {
            name: 'Jelly bean',
            calories: 375,
            fat: 0.0,
            carbs: 94,
            protein: 0.0,
          },
          {
            name: 'Lollipop',
            calories: 392,
            fat: 0.2,
            carbs: 98,
            protein: 0,
          },
          {
            name: 'Honeycomb',
            calories: 408,
            fat: 3.2,
            carbs: 87,
            protein: 6.5,
          },
          {
            name: 'Donut',
            calories: 452,
            fat: 25.0,
            carbs: 51,
            protein: 4.9,
          },
          {
            name: 'KitKat',
            calories: 518,
            fat: 26.0,
            carbs: 65,
            protein: 7,
          },
        ]
      },
      onEdit: function(item) {
        this.editedIndex = this.desserts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      onDelete: function(item) {
        const index = this.desserts.indexOf(item)
        confirm('Are you sure you want to delete this item?') && this.desserts.splice(index, 1)
      },
      close: function() {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      save: function() {
        if (this.editedIndex > -1) {
          Object.assign(this.desserts[this.editedIndex], this.editedItem)
        } else {
          this.desserts.push(this.editedItem)
        }
        this.close()
      },
    },
    created: function() {
      this.initialize()
    },
  }
</script>
<style scoped lang="scss">
  @import "./staff_list.scss";
</style>