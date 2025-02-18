<template>
    <div class="booking-container">
      <h1>Book a Service</h1>
      <p>You're booking: <strong>{{ selectedService }}</strong></p>
  
      <form @submit.prevent="submitBooking">
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
  export default {
    data() {
      return {
        selectedService: "",
        booking: {
          date: "",
          time: ""
        },
        availableTimes: [
          "09:00 AM",
          "10:00 AM",
          "11:00 AM",
          "01:00 PM",
          "02:00 PM",
          "03:00 PM",
          "04:00 PM"
        ]
      };
    },
    created() {
      this.selectedService = this.$route.params.service || "Unknown Service";
    },
    methods: {
      submitBooking() {
        if (!this.booking.date || !this.booking.time) {
          alert("Please select a date and time.");
          return;
        }
  
        alert(`Booking confirmed for ${this.selectedService} on ${this.booking.date} at ${this.booking.time}.`);
        
        // Here you would send the data to the backend
        // Example:
        // axios.post("/api/bookings", { service: this.selectedService, date: this.booking.date, time: this.booking.time })
        //     .then(response => console.log("Booking Successful", response));
        
        this.$router.push("/services"); // Redirect back after booking
      }
    }
  };
  </script>
  
  <style scoped>
  .booking-container {
    text-align: center;
    padding: 40px;
    background: #f8f9fa;
  }
  
  h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
  }
  
  p {
    font-size: 1.2rem;
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    font-weight: bold;
  }
  
  input, select {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  button {
    background: #007bff;
    color: white;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
    border-radius: 5px;
  }
  
  button:hover {
    background: #0056b3;
  }
  </style>
  