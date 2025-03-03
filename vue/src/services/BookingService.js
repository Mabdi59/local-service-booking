import axios from "axios";

const API_URL = "http://localhost:9000/api/bookings"; 

export default {
  getAllBookings() {
    return axios.get(API_URL);
  },
  getUserBookings(userId) {
    return axios.get(`${API_URL}/user/${userId}`);
  },
  createBooking(booking) {
    return axios.post(API_URL, booking);
  },
  deleteBooking(id) {
    return axios.delete(`${API_URL}/${id}`);
  }
};
