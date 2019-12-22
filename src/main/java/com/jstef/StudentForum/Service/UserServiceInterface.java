package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    User findByUserName(String userName);
    List<User> findAll();
    User saveNewUser(User user);
    User updateUser(User user);
    void deleteById(int Id);
    User findById(int Id);
    int findBiggestId();
    List<User> findBySignedUp(Topic topic);
}
