package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.dtos.GroupUserDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.repositories.GroupUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class GroupUserServiceTest {

    @InjectMocks
    private GroupUserService service;

    @Mock
    private UserService userService;

    @Mock
    private GroupService groupService;

    @Mock
    private GroupUserRepository repository;

    private List<GroupUser> groupUserList = new ArrayList<>();
    private final GroupUser groupUser = new GroupUser();

    @BeforeEach
    public void setup() {
        User user = new User();
        user.setId(UUID.fromString("700ca8dd-fcbe-4826-8b0a-17a54e8f382d"));

        Group group = new Group();
        group.setId(UUID.fromString("3d2a7558-2401-4c44-8303-4d236666f8f2"));

        groupUser.setId(UUID.fromString("5d6275b3-4ee3-4bee-8aa2-736c27276b76"));
        groupUser.setUser(user);
        groupUser.setGroup(group);

        groupUserList = Collections.singletonList(groupUser);
    }

    @Test
    public void mustReturnSuccess_FindByGroup() {
        when(repository.findUsersByGroupId(any())).thenReturn(groupUserList);

        GroupUserDTO result = service.findByGroup(UUID.randomUUID());

        assertNotNull(result);
        verify(repository, atLeastOnce()).findUsersByGroupId(any());

    }

    @Test
    public void mustReturnSuccess_WhenSave() {
        when(repository.save(any())).thenReturn(groupUser);
        when(userService.findByUUID(any())).thenReturn(new User());
        when(groupService.findByUUID(any())).thenReturn(new Group());

        boolean result = service.save(groupUser.getGroup().getId(), groupUser.getUser().getId());

        assertTrue(result);
        verify(repository, atLeastOnce()).save(any());
        verify(userService, atLeastOnce()).findByUUID(any());
        verify(groupService, atLeastOnce()).findByUUID(any());
    }

    @Test
    public void mustReturnError_WhenSave() {
        when(repository.save(any())).thenReturn(groupUser);
        when(userService.findByUUID(any())).thenReturn(null);
        when(groupService.findByUUID(any())).thenReturn(null);

        boolean result = service.save(groupUser.getGroup().getId(), groupUser.getUser().getId());

        assertFalse(result);
        verify(userService, atLeastOnce()).findByUUID(any());
        verify(groupService, atLeastOnce()).findByUUID(any());
    }

    @Test
    public void mustReturnSuccess_WhenRemoveUserFromGroup() {
        doNothing().when(repository).delete(any());
        when(repository.findByUserAndGroup(any(), any())).thenReturn(groupUser);

        boolean result = service.removeUserFromGroup(groupUser.getGroup().getId(), groupUser.getUser().getId());

        assertTrue(result);
        verify(repository, atLeastOnce()).findByUserAndGroup(any(), any());
        verify(repository, atLeastOnce()).delete(any());
    }

    @Test
    public void mustReturnError_WhenRemoveUserFromGroup() {
        doNothing().when(repository).delete(any());
        when(repository.findByUserAndGroup(any(), any())).thenReturn(null);

        boolean result = service.removeUserFromGroup(groupUser.getGroup().getId(), groupUser.getUser().getId());

        assertFalse(result);
        verify(repository, atLeastOnce()).findByUserAndGroup(any(), any());
    }
}
