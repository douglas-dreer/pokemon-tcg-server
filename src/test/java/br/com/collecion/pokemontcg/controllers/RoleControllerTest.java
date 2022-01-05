package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.dtos.GroupRoleDTO;
import br.com.collecion.pokemontcg.dtos.RoleDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.services.RoleGroupService;
import br.com.collecion.pokemontcg.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoleControllerTest {
    @InjectMocks
    private RoleController controller;

    @Mock
    private RoleService service;

    @Mock
    private RoleGroupService roleGroupService;

    private Role role = new Role();
    private RoleDTO roleDTO = new RoleDTO();

    private Group group = new Group();
    private GroupRoleDTO groupRoleDTO = new GroupRoleDTO();
    private List<Role> roleList = new ArrayList<>();

    @BeforeEach
    public void setup()  {
        group.setId(UUID.randomUUID());
        roleDTO.setId(UUID.randomUUID());

        role.setId(UUID.randomUUID());
        roleList = Collections.singletonList(role);
    }
    @Test
    public void mustReturnSuccess_WhenFindAll() {
        when(service.findAll()).thenReturn(roleList);

        ResponseEntity<List<Role>> result = controller.findAll();

        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertNotNull(result.getBody());
        assertFalse(result.getBody().isEmpty());
    }

    @Test
    public void mustReturnSuccess_WhenFindByUUID() {
        when(service.findByUUID(any())).thenReturn(role);
        ResponseEntity<Role> response = controller.findByUUID(UUID.randomUUID());

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(role, response.getBody());

        verify(service, atLeastOnce()).findByUUID(any());
    }

    @Test
    public void mustReturnSuccess_WhenSave() throws URISyntaxException {
        when(service.save(any())).thenReturn(role);
        ResponseEntity<?> response = controller.save(roleDTO);
        String location = String.format("/api/v1/config/roles/%s", role.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getHeaders().getLocation().toString());
        verify(service, atLeastOnce()).save(any());
    }

    @Test
    public void mustReturnInternalError_WhenSave() {
        when(service.save(any())).thenReturn(null);
        ResponseEntity<?> response = controller.save(roleDTO);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessagesEnum.INTERNAL_ERROR.getText(), response.getBody());
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {
        when(service.edit(any())).thenReturn(role);
        ResponseEntity<Role> response = controller.edit(UUID.randomUUID(), roleDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, atLeastOnce()).edit(any());
    }

    @Test
    public void mustReturnSuccess_WhenDelete() {
        when(service.delete(any())).thenReturn(true);
        ResponseEntity<Boolean> response = controller.delete(UUID.randomUUID());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(service, atLeastOnce()).delete(any());
    }

    @Test
    public void mustReturnSuccess_WhenAddRoleToGroup() {
        when(roleGroupService.save(any(), any())).thenReturn(true);
        ResponseEntity<?> response = controller.addRoleToGroup(role.getId(), group.getId());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(roleGroupService, atLeastOnce()).save(any(), any());
    }

    @Test
    public void mustReturnError_WhenAddRoleToGroup() {
        when(roleGroupService.save(any(), any())).thenReturn(false);
        String msgError = String.format(MessagesEnum.ERROR.getText(), "roleId", role.getId(), "groupId", group.getId());

        ResponseEntity<?> response = controller.addRoleToGroup(role.getId(), group.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msgError, response.getBody());
    }

    @Test
    public void mustReturnSuccess_WhenDeleteRoleFromGroup() {
        when(roleGroupService.removeRoleFromGroup(any(), any())).thenReturn(true);
        ResponseEntity<?> response = controller.deleteRoleFromGroup(role.getId(), group.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MessagesEnum.SUCCESS.getText(), response.getBody());
        verify(roleGroupService, atLeastOnce()).removeRoleFromGroup(any(), any());
    }

    @Test
    public void mustReturnError_WhenDeleteRoleFromGroup() {
        when(roleGroupService.removeRoleFromGroup(any(), any())).thenReturn(false);
        String msgError = String.format(MessagesEnum.ERROR.getText(), "roleId", role.getId(), "groupId", group.getId());

        ResponseEntity<String> response = controller.deleteRoleFromGroup(role.getId(), group.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msgError, response.getBody());
    }

    @Test
    public void mustReturnSuccess_WhenFindGroupsByRoleId() {
        when(roleGroupService.findGroupByRoleId(any())).thenReturn(groupRoleDTO);
        ResponseEntity<GroupRoleDTO> response = controller.findGroupsByRoleId(role.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(roleGroupService, atLeastOnce()).findGroupByRoleId(any());
    }
}
