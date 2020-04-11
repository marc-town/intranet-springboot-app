export default {
  methods: {
    isAdmin: function(role) {
      return role === 'ROLE_ADMIN';
    },
    isMiddle: function(role) {
      return role === 'ROLE_MIDDLE';
    },
    isUser: function(role) {
      return role === 'ROLE_USER';
    },
    getRoleColor: function(role) {
      if (this.isAdmin(role)) return 'red';
      else if (this.isMiddle(role)) return 'primary';
      else if (this.isUser(role)) return 'green accent-4';
    },
    getGradeColor: function(grade) {
      if (grade === 'PLATINUM') return ' #E5E4E2';
      else if (grade === 'GOLD') return 'yellow darken-1';
      else if (grade === 'SILVER') return 'grey lighten-1';
      else if (grade === 'BRONZE') return 'brown darken-3';
      else if (grade === 'GREEN') return 'green accent-4';
    },
  }
}