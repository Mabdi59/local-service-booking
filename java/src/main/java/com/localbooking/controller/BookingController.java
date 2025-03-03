package com.localbooking.controller;

import com.localbooking.model.Booking;
import com.localbooking.dao.BookingDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    private final BookingDao bookingDao;

    public BookingController(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable int userId) {
        return bookingDao.getBookingsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        Booking booking = bookingDao.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        int id = bookingDao.createBooking(booking);
        return ResponseEntity.ok("Booking created successfully with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        boolean deleted = bookingDao.deleteBooking(id);
        return deleted ? ResponseEntity.ok("Booking deleted successfully") : ResponseEntity.badRequest().body("Error deleting booking");
    }
}
