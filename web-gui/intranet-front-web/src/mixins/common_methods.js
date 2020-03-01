export default {
  isAdmin: function(role) {
    role === 'ROLE_ADMIN' ? true : false;
  },
  isMiddle: function(role) {
    role === 'ROLE_MIDDLE' ? true : false;
  },
  isUser: function(role) {
    role === 'ROLE_USER' ? true : false;
  },
}