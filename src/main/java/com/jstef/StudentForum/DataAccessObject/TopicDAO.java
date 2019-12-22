package com.jstef.StudentForum.DataAccessObject;

import com.jstef.StudentForum.Entity.Topic;
import com.jstef.StudentForum.Entity.User;
import com.jstef.StudentForum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class TopicDAO {
    @Autowired
    EntityManagerFactory managerFAC;

    @Autowired
    UserService userService;

    public Topic findById(int id) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        Topic topic = (Topic)manager.createQuery("select c from Topic c where c.id=:ID").setParameter("ID",id).getSingleResult();
        manager.getTransaction().commit();
        return topic;
    }

    public Topic saveTopic(Topic topic){
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(topic);
        manager.getTransaction().commit();
        return topic;
    }

    public List<Topic> findAll() {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        List<Topic>topics=manager.createQuery("select c from Topic c").getResultList();
        manager.getTransaction().commit();
        return topics;
    }

    public Topic deleteTopic(Topic topic) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.remove(topic);
        manager.getTransaction().commit();
        return topic;
    }

    public List<Topic> findByUserId(int id) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        User user = userService.findById(id);
        List<Topic>topics=user.getTopics();
        manager.getTransaction().commit();
        return topics;
    }

    public List<Topic> findByUserIdAndThreadId(int id, int threadId) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        List<Topic>topics=manager.createQuery("select c from Topic c where" +
                " c.threadId=:threadId and c.ownerId=:id").setParameter("threadId",threadId)
                .setParameter("id",id).getResultList();
        manager.getTransaction().commit();
        return topics;
    }

    public List<Topic> findBySubthreadId(int subthreadId) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        List<Topic>topics=manager.createQuery("select c from Topic c where c.subthread.id=:ID").setParameter("ID",subthreadId).getResultList();
        manager.getTransaction().commit();
        return topics;
    }

    public Topic updateTopic(Topic topic) {
        EntityManager manager = managerFAC.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(topic);
        manager.getTransaction().commit();
        return topic;
    }
}
