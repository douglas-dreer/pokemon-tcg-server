package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupUserDTOTest implements Models {
     private static final Group group = new Group();
     private static final User user = new User();
     private final List<User> userList = Collections.singletonList(user);

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        GroupUserDTO dto = new GroupUserDTO(group, userList);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        GroupUserDTO dto = new GroupUserDTO();
        dto.setGroup(group);
        dto.setUserList(userList);

        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        GroupUserDTO dto = GroupUserDTO.builder().group(group).userList(userList).build();
        check(dto);
    }

    private void check(GroupUserDTO item){
        assertNotNull(item, createMsg("GroupUserDTO"));
        assertEquals(group, item.getGroup(), createMsg("groupId"));
        assertEquals(userList, item.getUserList(), createMsg("userIdList"));
        assertFalse(item.getUserList().isEmpty(), createMsg("userIdList"));
    }
}
