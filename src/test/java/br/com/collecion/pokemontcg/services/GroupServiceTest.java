package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.repositories.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GroupServiceTest {
    @InjectMocks
    private GroupService service;

    @Mock
    private GroupRepository repository;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private Group group = new Group();
    private List<Group> groupList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        group = new Group(ID, "Administrator", new Date(), null, true);
        groupList = Collections.singletonList(group);
    }

    @Test
    void mustReturnSuccess_WhenFindByUUID() {
        Optional<Group> groupOptional = Optional.of(group);
        when(repository.findById(ID)).thenReturn(groupOptional);

        Group result = service.findByUUID(ID);

        assertNotNull(result);
        assertEquals(ID, group.getId());
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    void mustReturnSuccess_WhenFindAll() {
        when(repository.findAll()).thenReturn(groupList);
        List<Group> result = service.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void mustReturnSuccess_WhenFindByName() {
        Optional<Group> optionalGroup = Optional.of(group);
        when(repository.findByName(anyString())).thenReturn(optionalGroup);
        Group result = service.findByName("Admin");

        assertNotNull(result);
        verify(repository, atLeastOnce()).findByName(anyString());
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        when(repository.save(any())).thenReturn(group);

        Group result = service.save(group);

        assertNotNull(result);
        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    void mustReturnSuccess_WhenEdit() {
        group.setUpdateAt(new Date());
        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(group);

        Group result = service.edit(group);

        assertNotNull(result);
        verify(repository, atLeastOnce()).save(any());
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    void mustReturnError_WhenEdit_NotFound() {
        group.setUpdateAt(new Date());
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(group);

        Group result = service.edit(group);

        assertNull(result);
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    void mustReturnSuccess_WhenDelete() {
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(false);

        boolean result = service.delete(ID);

        assertTrue(result);
        verify(repository, atLeastOnce()).deleteById(any());
        verify(repository, atLeastOnce()).existsById(any());
    }
}
