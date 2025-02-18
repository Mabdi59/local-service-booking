import { createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import ServicesView from '../views/ServicesView.vue'; 
import BookingView from '../views/BookingView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also detects whether or not a route requires authentication.
 * If the user has not yet authenticated (and needs to), they are redirected to /login.
 * Otherwise, they can access the requested page.
 */

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/services",  
    name: "services",
    component: ServicesView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/book/:service",
    name: "book",
    component: BookingView,
    meta: { requiresAuth: true }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If authentication is required but the user is not logged in, redirect to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }

  // Otherwise, proceed as normal
});

export default router;
