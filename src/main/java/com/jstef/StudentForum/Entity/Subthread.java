package com.jstef.StudentForum.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//entity representing sub-section from forum
@Entity
@Table(name="subthreads")
public class Subthread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name="thread_id")
    private ForumThread thread;

    @OneToMany(mappedBy = "subthread",cascade = CascadeType.ALL)
    private List<Topic>topics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subthread subthread = (Subthread) o;
        return id == subthread.id &&
                active == subthread.active &&
                Objects.equals(name, subthread.name) &&
                Objects.equals(thread, subthread.thread) &&
                Objects.equals(topics, subthread.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active, thread, topics);
    }

    public Subthread(String name) {
        this.name = name;
    }

    public Subthread(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Subthread(){
        active=true;
        topics=new ArrayList<>();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public ForumThread getThread() {
        return thread;
    }

    public void setThread(ForumThread thread) {
        this.thread = thread;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
