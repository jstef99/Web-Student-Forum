package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.AllowedEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class AllowedEmailDAO {
    @Autowired
    EntityManagerFactory managerFactory;

    public AllowedEmail findByName(String name) {
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        try{
            AllowedEmail email = (AllowedEmail)manager.createQuery("select c from AllowedEmail c " +
                    "where c.name=:name").setParameter("name",name).getSingleResult();
            return email;
        }
        catch(Exception e){
            return null;
        }
        finally {
            manager.getTransaction().commit();
        }
    }

    public void updateEmail(AllowedEmail allowedEmail) {
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(allowedEmail);
        manager.getTransaction().commit();
    }
}
