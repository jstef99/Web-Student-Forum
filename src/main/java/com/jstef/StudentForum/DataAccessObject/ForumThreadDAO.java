package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.ForumThread;
import com.jstef.StudentForum.Service.SubthreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ForumThreadDAO {
    @Autowired
    EntityManagerFactory managerFAC;

    @Autowired
    SubthreadService subthreadService;

    public List<ForumThread> findAll(){
        List<ForumThread> threads = managerFAC.createEntityManager().createQuery("select c from ForumThread c").getResultList();
        return threads;
    }

    public ForumThread findById(int Id){
        ForumThread thread = (ForumThread)managerFAC.createEntityManager().createQuery("select c from ForumThread c where c.id=:Id")
                .setParameter("Id", Id).getSingleResult();
        return thread;
    }
    public ForumThread saveNewThread(ForumThread thread){
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(thread);
        manager.getTransaction().commit();
        return thread;
    }

    public ForumThread deleteThread(ForumThread thread) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.remove(thread);
        manager.getTransaction().commit();
        return thread;
    }

    public ForumThread deleteById(int id) {
        ForumThread forumThread = findById(id);
        subthreadService.deleteByThreadId(id);
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("delete from ForumThread c where c.id=:ID").setParameter("ID",id).executeUpdate();
        manager.getTransaction().commit();
        return forumThread;
    }
}
