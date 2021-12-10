package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.dtos.UserDTO;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.services.UserService;
import br.com.collecion.pokemontcg.utils.Converters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

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

    @Autowired
    private MockMvc mockMvc;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private User user = new User();
    private UserDTO dto = new UserDTO();
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        user = new User(
                ID, "Tabitha Rowe", "Simeon65@hotmail.com",
                "Richard.Swaniawski53", "Emmanuel_Kilback@hotmail.com",
                new Date(), null, true
        );
        userList = Collections.singletonList(user);
        dto = Converters.userEntityToUserDTO(user);
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() {
        when(service.findAll()).thenReturn(userList);
        ResponseEntity<List<User>> response = controller.findAll();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        verify(service, atLeastOnce()).findAll();
    }

    @Test
    public void mustReturnSuccess_WhenFindByUUID() {
        when(service.findByUUID(any())).thenReturn(user);
        ResponseEntity<User> response = controller.findByUUID(ID);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());

        verify(service, atLeastOnce()).findByUUID(ID);
    }

    @Test
    public void mustReturnSuccess_WhenSave() throws Exception {
        when(service.save(any())).thenReturn(user);
        ResponseEntity<User> response = controller.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());

        verify(service, atLeastOnce()).save(any());
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {
        when(service.edit(any())).thenReturn(user);
        ResponseEntity<User> response = controller.edit(ID, dto);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(user, response.getBody());

        verify(service, atLeastOnce()).edit(any());
    }

    @Test
    public void mustReturnSuccess_WhenDelete() {
        when(service.delete(ID)).thenReturn(true);
        ResponseEntity<Boolean> response = controller.delete(ID);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(true, response.getBody());

        verify(service, atLeastOnce()).delete(ID);
    }
}
