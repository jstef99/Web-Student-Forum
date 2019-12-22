package com.jstef.StudentForum.DAO;

import com.jstef.StudentForum.DataAccessObject.TokenDAO;
import com.jstef.StudentForum.Entity.Token;
import com.jstef.StudentForum.Service.TokenService;
import com.jstef.StudentForum.Service.TokenServiceInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TokenTest {

    @MockBean
    private TokenDAO tokenDAO;

    @Before
    public void setUp() {
        Token token = new Token("sample");
        Mockito.when(tokenDAO.findByName(token.getName()))
                .thenReturn(token);
        Mockito.when(tokenDAO.saveToken(token)).thenReturn(token);
    }

    @Test
    public void shouldReturnTokenForValidName(){
        Token token = new Token("sample");
        Token desired = tokenDAO.findByName(token.getName());
        assertEquals(token,desired);
    }

    @Test
    public void shouldReturnNoResultForInvalidName(){
        Token desired = tokenDAO.findByName("non-existing");
        assertNull(desired);
    }

    @Test
    public void shouldReturnTokenWhenSuccessfulSave(){
        Token token = new Token("sample");
        Token desired = tokenDAO.saveToken(token);
        assertEquals(token,desired);
    }
}
