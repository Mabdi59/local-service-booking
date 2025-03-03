import { createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import ServicesView from '../views/ServicesView.vue'; 
import BookingView from '../views/BookingView.vue';
import MyBookingsView from '../views/MyBookingsView.vue';

/**
 * The Vue Router is used to navigate between pages dynamically in a Vue SPA.
 * It also ensures protected routes require authentication.
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
    path: "/my-bookings",
    name: "myBookings",
    component: MyBookingsView,
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

// Create the router instance
const router = createRouter({
  history: createWebHistory(),
  routes
});

// Navigation guard to check authentication before entering a route
router.beforeEach((to) => {
  const store = useStore(); // Get Vuex store

  const requiresAuth = to.matched.some(route => route.meta.requiresAuth);

  if (requiresAuth && !store.state.token) {
    return { name: "login" };
  }
});

export default router;
