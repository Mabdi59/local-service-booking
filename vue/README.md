# Local Service Booking System - Vue Frontend

This is the Vue starter project for the Local Service Booking System. This document walks you through how to set up and run the project. It also explains the project's features, such as Vue Router, Vuex, and authentication.

## Project setup

The first thing you'll need to do is to download any dependencies by running this command:

```
npm install
```

Next, take a moment to review the `.env` file that's located in the root of the project. You can store environment variables that you want to use throughout your application in this file. When you open it, it'll look like this:

```
VITE_REMOTE_API=http://localhost:9000
```

*Note:* The Java Spring Boot application is configured to run on port 9000 instead of 8080.

Start your Vue application with the following command:

```
npm run dev
```

## Authentication

When you first run the project and visit the base URL, you're taken to a login page. This is because the home route `/` is secured by default. If you look in `src/router/index.js`, you'll see the following code:

```js
router.beforeEach((to) => {
  // Vuex code...
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});
```

This is a feature of Vue Router called [Navigation Guards](https://router.vuejs.org/guide/advanced/navigation-guards.html). 

### Vuex

The state for this application is stored in `src/store/index.js` using Vuex. The state object has two values: `token` and `user`. When you log in, the back-end service returns an authentication token along with your user credentials.

The authentication token is sent in the `Authorization` header to verify your identity. To persist this token when the application is closed or the page is refreshed, you'll store the token in local storage.

```js
// src/main.js
const currentToken = localStorage.getItem('token');
if (currentToken) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

// src/store/index.js
export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {}
    },
    // ...
  });
}
```

### Login

The `login()` method uses the `src/services/AuthService.js` to send a `POST` request to your API's `/login` route.

```js
import axios from 'axios';
const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {
  login(user) {
    return http.post('/login', user);
  },
  register(user) {
    return http.post('/register', user);
  }
};
```

When authentication is successful, the application stores the authentication token in Vuex and redirects the user to the home page:

```js
// src/views/LoginView.vue
login() {
  authService.login(this.user).then(response => {
    if (response.status == 200) {
      this.$store.commit("SET_AUTH_TOKEN", response.data.token);
      this.$store.commit("SET_USER", response.data.user);
      this.$router.push("/");
    }
  });
}
```

### Logout

Logging out clears the authentication token and redirects the user to the login page:

```js
// src/views/LogoutView.vue
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
```

```js
mutations: {
  LOGOUT(state) {
    localStorage.removeItem('token');
    state.token = '';
    state.user = {};
    axios.defaults.headers.common = {};
  }
}
```

### Register

The `register()` method calls the back-end `/register` route to create a new user:

```js
// src/views/RegisterView.vue
methods: {
  register() {
    authService.register(this.user).then((response) => {
      if (response.status == 201) {
        this.$router.push({ path: '/login', query: { registration: 'success' }});
      }
    });
  }
}
```

This Vue project is now updated and structured for the Local Service Booking System.

