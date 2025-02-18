package com.localbooking.dao;

import com.localbooking.model.RegisterUserDto;
import com.localbooking.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
