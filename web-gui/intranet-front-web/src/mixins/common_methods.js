export default {
  isAdmin: function(role) {
    return role === 'ROLE_ADMIN';
  },
  isMiddle: function(role) {
    return role === 'ROLE_MIDDLE';
  },
  isUser: function(role) {
    return role === 'ROLE_USER';
  },
}