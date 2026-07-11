package com.inn.cafe.service;

import com.inn.cafe.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<Map<String, String>> signUp(Map<String, String> requestMap);

    User saveUser(User user);

    List<User> getAllUser();

    User getByUserId(Integer id);

    User updateUser(Integer id, User user);

    void deleteUser(Integer id);
}