package com.jstef.StudentForum.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


//entity representing forum section
@Entity
@Table(name="threads")
public class ForumThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="thread",cascade = CascadeType.ALL)
    private List<Subthread> subthreads;

    public List<Subthread> getSubthreads() {
        return subthreads;
    }

    public void setSubthreads(List<Subthread> subthreads) {
        this.subthreads = subthreads;
    }

    public ForumThread(){
        subthreads = new ArrayList<>();
    }

    public void addTopic(Subthread subthread){
        subthreads.add(subthread);
    }

    public ForumThread(String name) {
        this.subthreads=new ArrayList<>();
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
}
