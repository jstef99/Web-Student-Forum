package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.Subthread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class SubthreadDAO {
    @Autowired
    EntityManagerFactory managerFAC;

    public void saveNewSubthread(Subthread subthread) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(subthread);
        manager.getTransaction().commit();
    }

    public Subthread findById(int subthreadId) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        Subthread subthread = (Subthread)manager.createQuery("select c from Subthread c where c.id=:ID").setParameter("ID",subthreadId).getSingleResult();
        manager.getTransaction().commit();
        return subthread;
    }


    public void deleteByThreadId(int id) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("delete from Topic c where c.threadId=:ID").setParameter("ID",id).executeUpdate();
        manager.createQuery("delete from Subthread c where c.thread.id=:ID").setParameter("ID",id).executeUpdate();
        manager.getTransaction().commit();
    }

    public List<Subthread> findAll() {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        List<Subthread>subthreads = manager.createQuery("select c from Subthread c").getResultList();
        manager.getTransaction().commit();
        return subthreads;
    }

    public void deleteById(int id) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("delete from Topic c where c.subthread.id=:ID").setParameter("ID",id).executeUpdate();
        manager.createQuery("delete from Subthread c where c.thread.id=:ID").setParameter("ID",id).executeUpdate();
        manager.getTransaction().commit();
    }
}
