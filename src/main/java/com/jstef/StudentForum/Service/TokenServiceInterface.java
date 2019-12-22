package com.jstef.StudentForum.Service;

import com.jstef.StudentForum.Entity.Token;

public interface TokenServiceInterface {
    public Token findByName(String name);
    public Token saveToken(Token token);
}
