package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.UserDAO;
import com.jstef.StudentForum.Entity.Role;
import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if(!user.isEnabled()){
            throw new UsernameNotFoundException("Please activate your account via email.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        User user = userDAO.findByUserName(userName);
        return user;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        List<User>users=userDAO.findAll();
        return users;
    }

    @Override
    @Transactional
    public void saveNewUser(User user) {
        userDAO.saveNewUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteById(int Id) {
        userDAO.deleteById(Id);
    }
    @Override
    @Transactional
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public int findBiggestId() {
        return userDAO.findBiggestId();
    }

    @Override
    public List<User> findBySignedUp(Topic topic) {
        return userDAO.findBySignedUp(topic);
    }
}
