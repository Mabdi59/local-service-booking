<template>
  <div class="home-container">
    <div class="home-content">
      <h1>Welcome to Local Service Booking</h1>
      <p>Book and manage plumbing, electrical, HVAC, and other services effortlessly.</p>
      <router-link to="/services" class="btn">Explore Services</router-link>
    </div>
  </div>
</template>

<script>
export default {
};
</script>

<style scoped>
.home-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to right, #4facfe, #00f2fe);
  color: white;
  text-align: center;
}

.home-content {
  max-width: 600px;
  padding: 20px;
  border-radius: 10px;
}

h1 {
  font-size: 2.5rem;
  margin-bottom: 10px;
}

p {
  font-size: 1.2rem;
  margin-bottom: 20px;
}

.btn {
  display: inline-block;
  padding: 10px 20px;
  font-size: 1rem;
  color: white;
  background-color: #007bff;
  border-radius: 5px;
  text-decoration: none;
  transition: background 0.3s ease;
}

.btn:hover {
  background-color: #0056b3;
}
</style>

