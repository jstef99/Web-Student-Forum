package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class RoleDAO {
    @Autowired
    EntityManagerFactory managerFAC;

    public Role findRoleByName(String roleName){
        EntityManager manager = managerFAC.createEntityManager();
        Role role = (Role)manager.createQuery("select c from Role c where c.name=:rname").setParameter("rname",roleName).getSingleResult();
        return role;
    }
}
