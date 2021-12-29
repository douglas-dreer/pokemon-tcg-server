package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.dtos.GroupDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.services.GroupService;
import br.com.collecion.pokemontcg.services.GroupUserService;
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
public class GroupControllerTest {

    @InjectMocks
    private GroupController controller;

    @Mock
    private GroupService service;

    @Mock
    private GroupUserService groupUserService;

    @Autowired
    private MockMvc mockMvc;

    private static final UUID ID = UUID.fromString("37ca882d-8550-43b3-9a12-597d17885b64");
    private Group group = new Group();
    private GroupDTO dto = new GroupDTO();
    private List<Group> userList = new ArrayList<>();

    private final User user = new User();
    private final GroupUser groupUser = new GroupUser();

    @BeforeEach
    public void setup() {
        group = new Group(ID, "Administrator", new Date(), null, true);
        userList = Collections.singletonList(group);
        dto = Converters.groupEntityToGroupDTO(group);

        user.setId(UUID.randomUUID());
        groupUser.setUser(user);
        groupUser.setGroup(group);
    }

    @Test
    public void mustReturnSuccess_WhenFindAll() {
        when(service.findAll()).thenReturn(userList);
        ResponseEntity<List<Group>> response = controller.findAll();

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isEmpty());

        verify(service, atLeastOnce()).findAll();
    }

    @Test
    public void mustReturnSuccess_WhenFindByUUID() {
        when(service.findByUUID(any())).thenReturn(group);
        ResponseEntity<Group> response = controller.findByUUID(ID);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(group, response.getBody());

        verify(service, atLeastOnce()).findByUUID(ID);
    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        when(service.save(any())).thenReturn(group);
        ResponseEntity<Group> response = controller.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(group, response.getBody());

        verify(service, atLeastOnce()).save(any());
    }

    @Test
    public void mustReturnSuccess_WhenEdit() {
        when(service.edit(any())).thenReturn(group);
        ResponseEntity<Group> response = controller.edit(ID, dto);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(group, response.getBody());

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

    @Test
    public void mustReturnSuccess_WhenAddUserToGroup() {
        when(groupUserService.save(any(), any())).thenReturn(true);

        ResponseEntity<String> response = controller.addUserToGroup(group.getId(), user.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MessagesEnum.SUCCESS.getText(), response.getBody());
    }

    @Test
    public void mustReturnError_WhenAddUserToGroup() {
        when(groupUserService.save(any(), any())).thenReturn(false);
        String msgError = String.format(MessagesEnum.ERROR.getText(), "groupId", group.getId(), "userId", user.getId());

        ResponseEntity<String> response = controller.addUserToGroup(group.getId(), user.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msgError, response.getBody());
    }

    @Test
    public void mustReturnSuccess_WhenDeleteUserFromGroup() {
        when(groupUserService.removeUserFromGroup(any(), any())).thenReturn(true);

        ResponseEntity<String> response = controller.deleteUserFromGroup(group.getId(), user.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MessagesEnum.SUCCESS.getText(), response.getBody());
    }

    @Test
    public void mustReturnError_WhenDeleteUserFromGroup() {
        when(groupUserService.removeUserFromGroup(any(), any())).thenReturn(false);
        String msgError = String.format(MessagesEnum.ERROR.getText(), "groupId", group.getId(), "userId", user.getId());

        ResponseEntity<String> response = controller.deleteUserFromGroup(group.getId(), user.getId());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(msgError, response.getBody());
    }

}
