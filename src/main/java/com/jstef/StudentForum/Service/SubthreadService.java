package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.SubthreadDAO;
import com.jstef.StudentForum.Entity.Subthread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubthreadService implements SubthreadServiceInterface {
    @Autowired
    SubthreadDAO subthreadDAO;

    @Override
    @Transactional
    public Subthread findById(int subthreadId) {
        return subthreadDAO.findById(subthreadId);
    }

    @Override
    public void deleteByThreadId(int id) {
        subthreadDAO.deleteByThreadId(id);
    }

    @Override
    public List<Subthread> findAll() {
        return subthreadDAO.findAll();
    }

    @Override
    public void deleteById(int id) {
        subthreadDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void saveNewSubthread(Subthread subthread) {
        subthreadDAO.saveNewSubthread(subthread);
    }
}
