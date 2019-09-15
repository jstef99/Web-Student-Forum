package com.jstef.StudentForum.Entity;

import com.jstef.StudentForum.Validation.StrongPassword;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//entity representing single forum user
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    @Size(min=4,message = "Username must be at least 4 character long")
    @NotNull(message="Username cannot be empty")
    private String username;

    @Column(name="password")
    @NotNull(message="Password cannot be empty")
    @StrongPassword
    private String password;

    @Column(name="email")
    @NotNull(message="Cannot be empty")
    private String email;

    @Column(name="active")
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_topics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private List<Topic> topics;

    public User(){

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email=email;
    }

    public void signUpForTopic(Topic topic){
        if(topics.contains(topic)){
            topics.remove(topic);
        }
        else {
            topics.add(topic);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Topic> getTopicsFromThread(int id){
        List<Topic>tmp=new ArrayList<>();
        for(Topic topic:topics){
            if(topic.getThreadId()==id){
                tmp.add(topic);
            }
        }
        return tmp;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
