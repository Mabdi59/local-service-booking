<template>
  <div class="services-container">
    <h1>Our Services</h1>
    <p>Select a service and book your appointment today.</p>

    <div class="services-list">
      <div v-for="service in services" :key="service.id" class="service-card">
        <img :src="service.image" :alt="service.name" class="service-image" />
        <h2>{{ service.name }}</h2>
        <p>{{ service.description }}</p>
        <button @click="bookService(service.name)">Book Now</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      services: [
        {
          id: 1,
          name: "Plumbing",
          description: "Reliable plumbing solutions for your home and business.",
          image: "https://via.placeholder.com/300x200/007bff/ffffff?text=Plumbing",
        },
        {
          id: 2,
          name: "Electrical",
          description: "Professional electrical repairs and installations.",
          image: "https://via.placeholder.com/300x200/ffc107/000000?text=Electrical",
        },
        {
          id: 3,
          name: "HVAC",
          description: "Heating and cooling system maintenance and repairs.",
          image: "https://via.placeholder.com/300x200/28a745/ffffff?text=HVAC",
        }
      ]
    };
  },
  methods: {
    bookService(serviceName) {
      this.$router.push(`/book/${serviceName.toLowerCase()}`);
    }
  }
};
</script>

<style scoped>
.services-container {
  text-align: center;
  padding: 50px 20px;
  background: #f8f9fa;
}

h1 {
  font-size: 2.8rem;
  margin-bottom: 15px;
  color: #333;
}

p {
  font-size: 1.2rem;
  margin-bottom: 30px;
  color: #555;
}

.services-list {
  display: flex;
  justify-content: center;
  gap: 25px;
  flex-wrap: wrap;
}

.service-card {
  background: white;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.15);
  width: 300px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.service-card:hover {
  transform: translateY(-5px);
  box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.2);
}

.service-image {
  width: 100%;
  height: 180px;
  object-fit: cover;
  border-radius: 8px;
}

button {
  background: #007bff;
  color: white;
  border: none;
  padding: 12px 18px;
  margin-top: 15px;
  cursor: pointer;
  border-radius: 6px;
  font-size: 1rem;
  transition: background 0.3s ease;
}

button:hover {
  background: #0056b3;
}
</style>
