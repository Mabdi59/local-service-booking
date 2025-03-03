<template>
  <div class="bookings-container">
    <h1>My Bookings</h1>

    <div v-if="bookings.length > 0">
      <div v-for="booking in bookings" :key="booking.id" class="booking-card">
        <h2>{{ booking.serviceName }}</h2>
        <p>Name: {{ booking.customerName }}</p>
        <p>Date: {{ booking.date }}</p>
        <p>Time: {{ booking.time }}</p>
        <button @click="deleteBooking(booking.id)">Cancel</button>
      </div>
    </div>

    <p v-else>No bookings yet. <router-link to="/services">Book a service</router-link></p>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters("booking", ["allBookings"])
  },
  methods: {
    ...mapActions("booking", ["deleteBooking"])
  }
};
</script>

<style scoped>
.bookings-container {
  text-align: center;
  padding: 40px;
}

.booking-card {
  background: white;
  padding: 15px;
  border-radius: 8px;
  margin: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

button {
  background: red;
  color: white;
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 5px;
}

button:hover {
  background: darkred;
}
</style>
