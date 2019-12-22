package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.Entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService implements TokenServiceInterface {
    @Autowired
    TokenDAO tokenDAO;

    @Override
    @Transactional
    public Token findByName(String name) {
        return tokenDAO.findByName(name);
    }

    @Override
    @Transactional
    public Token saveToken(Token token) {
        return tokenDAO.saveToken(token);
    }
}
