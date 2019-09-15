package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.RoleDAO;
import com.jstef.StudentForum.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService implements RoleServiceInterface{
    @Autowired
    RoleDAO roleDAO;

    @Override
    @Transactional
    public Role findRoleByName(String name) {
        return roleDAO.findRoleByName(name);
    }
}
