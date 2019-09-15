package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.AllowedEmailDAO;
import com.jstef.StudentForum.Entity.AllowedEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AllowedEmailService implements AllowedEmailServiceInterface{
    @Autowired
    private AllowedEmailDAO allowedEmailDAO;

    @Override
    @Transactional
    public AllowedEmail findByName(String name) {
        return allowedEmailDAO.findByName(name);
    }

    @Override
    @Transactional
    public void UpdateEmail(AllowedEmail allowedEmail) {
        allowedEmailDAO.updateEmail(allowedEmail);
    }
}
