package com.localbooking.dao;

import com.localbooking.model.Booking;
import java.util.List;

public interface BookingDao {
    List<Booking> getAllBookings();
    List<Booking> getBookingsByUserId(int userId);
    Booking getBookingById(int id);
    int createBooking(Booking booking);
    boolean deleteBooking(int id);
}
