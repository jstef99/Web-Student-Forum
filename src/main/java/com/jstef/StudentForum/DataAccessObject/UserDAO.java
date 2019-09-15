package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import com.jstef.StudentForum.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {
    @Autowired
    EntityManagerFactory managerFAC;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    RoleService roleService;

    public void saveNewUser(User user){
        EntityManager manager = managerFAC.createEntityManager();
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(this.findAll());
        if(this.findAll().size()==0){
            user.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER"), roleService.findRoleByName("ROLE_ADMIN")));
        }
        else {
            user.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER")));
        }
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    public void updateUser(User user){
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    public User findByUserName(String userName){
        EntityManager manager = managerFAC.createEntityManager();
        try {
            User user = (User) manager.createQuery("select c from User c where c.username=:uname").setParameter("uname", userName).getSingleResult();
            return user;
        }
        catch(Exception e){
            return null;
        }
    }
    public User findById(int Id) {
        EntityManager manager = managerFAC.createEntityManager();
        User user = (User)manager.createQuery("select c from User c where c.id=:uId").setParameter("uId",Id).getSingleResult();
        return user;
    }
    public List<User> findAll(){
        EntityManager manager = managerFAC.createEntityManager();
        List<User> users = manager.createQuery("select c from User c").getResultList();
        return users;
    }
    public void deleteById(int Id){
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("delete from User c where c.id=:Id").setParameter("Id",Id).executeUpdate();
        manager.getTransaction().commit();
    }

    public int findBiggestId() {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        int biggestId = (int)manager.createQuery("select MAX(id) from User").getSingleResult();
        manager.getTransaction().commit();
        return biggestId;
    }

    public List<User> findBySignedUp(Topic topic) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        List<User> users = manager.createQuery("select c from User c where :topic in elements(c.topics)").setParameter("topic",topic).getResultList();
        manager.getTransaction().commit();
        return users;
    }
}
