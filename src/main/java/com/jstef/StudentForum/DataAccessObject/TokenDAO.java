package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class TokenDAO {
    @Autowired
    private EntityManagerFactory managerFactory;

    public Token findByName(String name){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        Token token = (Token)manager.createQuery("select c from Token c where c.name=:name").setParameter("name",name).getSingleResult();
        manager.getTransaction().commit();
        return token;
    }

    public void saveToken(Token token){
        EntityManager manager = managerFactory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(token);
        manager.getTransaction().commit();
    }
}
