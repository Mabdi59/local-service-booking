package com.localbooking.dao;

import com.localbooking.model.Booking;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.util.List;

@Component
public class JdbcBookingDao implements BookingDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBookingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Booking> getAllBookings() {
        String sql = "SELECT * FROM bookings ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Booking(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("service_name"),
                rs.getString("customer_name"),
                rs.getString("date"),
                rs.getString("time"),
                rs.getTimestamp("created_at").toLocalDateTime()
        ));
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Booking(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("service_name"),
                rs.getString("customer_name"),
                rs.getString("date"),
                rs.getString("time"),
                rs.getTimestamp("created_at").toLocalDateTime()
        ), userId);
    }

    @Override
    public Booking getBookingById(int id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Booking(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("service_name"),
                rs.getString("customer_name"),
                rs.getString("date"),
                rs.getString("time"),
                rs.getTimestamp("created_at").toLocalDateTime()
        ), id);
    }

    @Override
    public int createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, service_name, customer_name, date, time) VALUES (?, ?, ?, ?, ?) RETURNING id";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, booking.getUserId());
            ps.setString(2, booking.getServiceName());
            ps.setString(3, booking.getCustomerName());
            ps.setString(4, booking.getDate());
            ps.setString(5, booking.getTime());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
