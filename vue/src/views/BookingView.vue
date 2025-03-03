<template>
  <div class="booking-container">
    <h1>Book a Service</h1>
    <p>You're booking: <strong>{{ selectedService }}</strong></p>

    <form @submit.prevent="submitBooking">
      <div class="form-group">
        <label for="customerName">Your Name:</label>
        <input type="text" id="customerName" v-model="booking.customerName" required />
      </div>

      <div class="form-group">
        <label for="date">Select a Date:</label>
        <input type="date" id="date" v-model="booking.date" required />
      </div>

      <div class="form-group">
        <label for="time">Select a Time:</label>
        <select id="time" v-model="booking.time" required>
          <option v-for="time in availableTimes" :key="time" :value="time">
            {{ time }}
          </option>
        </select>
      </div>

      <button type="submit">Confirm Booking</button>
    </form>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      selectedService: "",
      booking: {
        customerName: "",
        date: "",
        time: "",
        userId: 1 
      },
      availableTimes: ["09:00 AM", "10:00 AM", "11:00 AM", "01:00 PM", "02:00 PM", "03:00 PM"]
    };
  },
  created() {
    this.selectedService = this.$route.params.service || "Unknown Service";
  },
  methods: {
    ...mapActions("booking", ["addBooking"]),

    async submitBooking() {
      if (!this.booking.customerName || !this.booking.date || !this.booking.time) {
        alert("Please complete all fields.");
        return;
      }

      const newBooking = {
        serviceName: this.selectedService,
        customerName: this.booking.customerName,
        date: this.booking.date,
        time: this.booking.time,
        userId: this.booking.userId
      };

      await this.addBooking(newBooking);
      alert(`Booking confirmed for ${this.selectedService} on ${this.booking.date} at ${this.booking.time}.`);
      this.$router.push("/my-bookings");
    }
  }
};
</script>

<style scoped>
.booking-container {
  text-align: center;
  padding: 40px;
}

.form-group {
  margin-bottom: 15px;
}

input, select {
  width: 100%;
  padding: 8px;
}
</style>
