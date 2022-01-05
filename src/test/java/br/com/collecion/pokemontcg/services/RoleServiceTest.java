package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository repository;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private Role role = new Role();
    private List<Role> roleList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        role = new Role(ID, "Role 001", new Date(), new Date(), true);
        roleList = Collections.singletonList(role);
    }

    @Test
    void mustReturnSuccess_WhenFindAll() {
        when(repository.findAll()).thenReturn(roleList);
        List<Role> results = roleService.findAll();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void mustReturnSuccess_WhenFindByUUID() {
        Optional<Role> roleOptional = Optional.of(role);
        when(repository.findById(ID)).thenReturn(roleOptional);

        Role result = roleService.findByUUID(ID);

        assertNotNull(result);
        assertEquals(ID, result.getId());
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        when(repository.save(any())).thenReturn(role);

        Role result = roleService.save(role);

        assertNotNull(result);
        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    void mustReturnSuccess_WhenEdit() {
        role.setUpdateAt(new Date());
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(role);

        Role result = roleService.edit(role);

        assertNotNull(result);
        verify(repository, atLeastOnce()).save(any());
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    void mustReturnError_WhenEdit_NotFound() {
        role.setUpdateAt(new Date());
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(role);

        Role result = roleService.edit(role);

        assertNull(result);
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    void mustReturnSuccess_WhenDelete() {
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(false);

        boolean result = roleService.delete(ID);

        assertTrue(result);
        verify(repository, atLeastOnce()).deleteById(any());
        verify(repository, atLeastOnce()).existsById(any());
    }
}
