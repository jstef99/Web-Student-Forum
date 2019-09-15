package com.jstef.StudentForum.Entity;

import javax.persistence.*;

//entity representing single forum topic
//it should be considered as an exercise for which users (students)
//can sign up
@Entity
@Table(name="topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

   // @Column(name="thread_id")
   // private int threadId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="subthread_id")
    private Subthread subthread;

    @Column(name="thread_id")
    private int threadId;

    @Column(name="owner_id")
    private int ownerId;

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return id==((Topic)o).getId();
    }

    public Topic(){

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subthread getSubthread() {
        return subthread;
    }

    public void setSubthread(Subthread subthread) {
        this.subthread = subthread;
    }

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
