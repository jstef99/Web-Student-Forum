package com.jstef.StudentForum.Entity;

import javax.persistence.*;
import java.util.Objects;

//entity used while confirming user during registration process
@Entity
@Table(name="tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="token")
    private String name;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return id == token.id &&
                Objects.equals(name, token.name) &&
                Objects.equals(user, token.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user);
    }

    public Token(){

    }

    public Token(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
