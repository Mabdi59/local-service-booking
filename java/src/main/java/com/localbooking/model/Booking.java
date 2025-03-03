package com.localbooking.model;

import java.time.LocalDateTime;

public class Booking {
    private int id;
    private int userId;
    private String serviceName;
    private String customerName;
    private String date;
    private String time;
    private LocalDateTime createdAt;

    // Constructors
    public Booking() {}

    public Booking(int id, int userId, String serviceName, String customerName, String date, String time, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.serviceName = serviceName;
        this.customerName = customerName;
        this.date = date;
        this.time = time;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
