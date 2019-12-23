package com.jstef.StudentForum.Entity;

import javax.persistence.*;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                threadId == topic.threadId &&
                ownerId == topic.ownerId &&
                Objects.equals(name, topic.name) &&
                Objects.equals(description, topic.description) &&
                Objects.equals(subthread, topic.subthread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, subthread, threadId, ownerId);
    }

    public Topic(){

    }

    public Topic(int id, int ownerId, int threadId){
        this.id = id;
        this.ownerId = ownerId;
        this.threadId = threadId;
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
