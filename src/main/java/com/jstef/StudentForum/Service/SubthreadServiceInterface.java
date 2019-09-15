package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.Subthread;

import java.util.List;

public interface SubthreadServiceInterface {
    void saveNewSubthread(Subthread subthread);
    Subthread findById(int subthreadId);
    void deleteByThreadId(int id);

    List<Subthread> findAll();

    void deleteById(int id);
}
