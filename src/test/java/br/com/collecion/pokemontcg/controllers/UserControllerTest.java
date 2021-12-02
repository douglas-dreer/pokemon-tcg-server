package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private User user = new User();
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        user = new User(
                ID, "Tabitha Rowe", "Simeon65@hotmail.com",
                "Richard.Swaniawski53", "Emmanuel_Kilback@hotmail.com",
                new Date(), null, true
        );
        userList = Collections.singletonList(user);
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() {
        when(service.findAll()).thenReturn(userList);
        ResponseEntity<List<User>> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        verify(service, atLeastOnce()).findAll();
    }

    @Test
    public void mustReturnSuccess_WhenFindByUUID() {
        when(service.findByUUID(any())).thenReturn(user);
        ResponseEntity<User> response = controller.findByUUID(ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());

        verify(service, atLeastOnce()).findByUUID(ID);
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        when(service.save(any())).thenReturn(user);
        ResponseEntity<User> response = controller.save(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());

        verify(service, atLeastOnce()).save(user);
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {
        when(service.edit(user)).thenReturn(user);
        ResponseEntity<User> response = controller.edit(ID, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());

        verify(service, atLeastOnce()).edit(user);
    }

    @Test
    public void mustReturnSuccess_WhenDelete() {
        when(service.delete(ID)).thenReturn(true);
        ResponseEntity<Boolean> response = controller.delete(ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(true, response.getBody());

        verify(service, atLeastOnce()).delete(ID);
    }
}
