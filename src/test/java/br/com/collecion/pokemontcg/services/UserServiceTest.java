package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
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
    void mustReturnSuccess_WhenFindById() {
        when(service.findByUUID(any())).thenReturn(user);
        User result = service.findByUUID(ID);

        assertNotNull(result);
        assertEquals(ID, user.getId());

        verify(service, atLeastOnce()).findByUUID(ID);
    }

    @Test
    void mustReturnSuccess_WhenFindAll() {
        when(service.findAll()).thenReturn(userList);

        List<User> result = service.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(service, atLeastOnce()).findAll();
    }

    @Test
    void mustReturnSuccess_WhenEdit() {
        user.setUpdateAt(new Date());
        when(service.edit(any())).thenReturn(user);

        User result = service.edit(user);

        assertNotNull(result);
        verify(service, atLeastOnce()).edit(user);
    }

    @Test
    void mustReturnSuccess_WhenDelete() {
        when(service.delete(any())).thenReturn(true);

        boolean result = service.delete(ID);

        assertTrue(result);
        verify(service, atLeastOnce()).delete(ID);
    }
}
