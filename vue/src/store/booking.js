import { createStore } from 'vuex';
import BookingService from '../services/BookingService';

export default createStore({
  state: {
    bookings: []
  },
  mutations: {
    SET_BOOKINGS(state, bookings) {
      state.bookings = bookings;
    },
    ADD_BOOKING(state, booking) {
      state.bookings.push(booking);
    },
    DELETE_BOOKING(state, id) {
      state.bookings = state.bookings.filter(b => b.id !== id);
    }
  },
  actions: {
    async fetchBookings({ commit }) {
      const response = await BookingService.getAllBookings();
      commit("SET_BOOKINGS", response.data);
    },
    async addBooking({ commit }, booking) {
      const response = await BookingService.createBooking(booking);
      booking.id = response.data.id;
      commit("ADD_BOOKING", booking);
    },
    async deleteBooking({ commit }, id) {
      await BookingService.deleteBooking(id);
      commit("DELETE_BOOKING", id);
    }
  },
  getters: {
    allBookings: state => state.bookings
  }
});
