package com.jstef.StudentForum.Entity;

import javax.persistence.*;

//Entity representing email which can be used during registration process
@Entity
@Table(name="allowed_emails")
public class AllowedEmail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="used")
    private boolean used;

    public AllowedEmail(){
        used=false;
    }

    public AllowedEmail(String name, boolean used) {
        this.name = name;
        this.used = used;
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
