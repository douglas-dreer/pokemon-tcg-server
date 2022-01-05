package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.dtos.GroupRoleDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.enities.RoleGroup;
import br.com.collecion.pokemontcg.repositories.GroupRepository;
import br.com.collecion.pokemontcg.repositories.RoleGroupRepository;
import br.com.collecion.pokemontcg.repositories.RoleRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

@SpringBootTest
public class RoleGroupServiceTest {
    @InjectMocks
    private RoleGroupService roleGroupService;
    
    @Mock
    private RoleGroupRepository repository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private GroupRepository groupRepository;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private Role role = new Role();
    private Group group = new Group();
    private RoleGroup roleGroup = new RoleGroup();
    private List<RoleGroup> roleGroupList = new ArrayList<>();
    
    @BeforeEach
    public void setup() {
        roleGroup.setRole(role);
        roleGroup.setGroup(group);
        roleGroup.setId(ID);
        roleGroupList = Collections.singletonList(roleGroup);
    }

    @Test
    void mustReturnSuccess_WhenFindAll() {
        when(repository.findAll()).thenReturn(roleGroupList);
        List<RoleGroup> results = roleGroupService.findAll();

        assertNotNull(results);
        assertFalse(results.isEmpty());
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void mustReturnSuccess_WhenFindByUUID() {
        Optional<RoleGroup> roleGroupOptional = Optional.of(roleGroup);
        when(repository.findById(ID)).thenReturn(roleGroupOptional);

        RoleGroup result = roleGroupService.findByUUID(ID);

        assertNotNull(result);
        assertEquals(ID, result.getId());
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    void mustReturnNotFound_WhenFindByUUID() {
        Optional<RoleGroup> roleOptional = Optional.ofNullable(null);
        when(repository.findById(ID)).thenReturn(roleOptional);

        RoleGroup result = roleGroupService.findByUUID(ID);

        assertNull(result);
        verify(repository, atLeastOnce()).findById(any());
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        Optional<Role> roleOptional = Optional.of(role);
        Optional<Group> groupOptional = Optional.of(group);

        when(roleRepository.findById(any())).thenReturn(roleOptional);
        when(groupRepository.findById(any())).thenReturn(groupOptional);

        when(repository.save(any())).thenReturn(roleGroup);

        boolean result = roleGroupService.save(ID, ID);

        assertTrue(result);
        verify(roleRepository, atLeastOnce()).findById(any());
        verify(groupRepository, atLeastOnce()).findById(any());
        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    public void mustReturnErrorNotFoundRole_WhenSave() {
        Optional<Role> roleOptional = Optional.ofNullable(null);
        Optional<Group> groupOptional = Optional.ofNullable(null);

        when(roleRepository.findById(any())).thenReturn(roleOptional);
        when(groupRepository.findById(any())).thenReturn(groupOptional);

        when(repository.save(any())).thenReturn(roleGroup);

        boolean result = roleGroupService.save(ID, ID);

        assertFalse(result);
        verify(roleRepository, atLeastOnce()).findById(any());
        verify(groupRepository, atLeastOnce()).findById(any());
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {

        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(roleGroup);

        RoleGroup result = roleGroupService.edit(roleGroup);

        assertNotNull(result);
        verify(repository, atLeastOnce()).existsById(any());
        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    public void mustReturnError_WhenEdit_NotFound() {
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(role);

        RoleGroup result = roleGroupService.edit(roleGroup);

        assertNull(result);
        verify(repository, atLeastOnce()).existsById(any());
    }

    @Test
    public void mustReturnSuccess_WhenDelete() {
        doNothing().when(repository).deleteById(any());
        when(repository.existsById(any())).thenReturn(false);

        boolean result = roleGroupService.delete(ID);

        assertTrue(result);
        verify(repository, atLeastOnce()).existsById(any());
        verify(repository, atLeastOnce()).deleteById(any());
    }

    @Test
    public void mustReturnSuccess_WhenRemoveRoleFromGroup() {
        when(repository.findByRoleAndGroup(any(), any())).thenReturn(roleGroup);
        doNothing().when(repository).delete(any());

        boolean result = roleGroupService.removeRoleFromGroup(ID, ID);

        assertTrue(result);

        verify(repository, atLeastOnce()).findByRoleAndGroup(any(), any());
        verify(repository, atLeastOnce()).delete(any());
    }

    @Test
    public void mustReturnError_WhenRemoveRoleFromGroup() {
        when(repository.findByRoleAndGroup(any(), any())).thenReturn(null);
        doNothing().when(repository).delete(any());

        boolean result = roleGroupService.removeRoleFromGroup(ID, ID);

        assertFalse(result);

        verify(repository, atLeastOnce()).findByRoleAndGroup(any(), any());
    }

    @Test
    public void mustReturnError_WhenFindGroupByRoleId() {
        when(repository.findGroupByRoleId(any())).thenReturn(roleGroupList);

        GroupRoleDTO result = roleGroupService.findGroupByRoleId(ID);

        assertNotNull(result);

        verify(repository, atLeastOnce()).findGroupByRoleId(any());
    }
}
