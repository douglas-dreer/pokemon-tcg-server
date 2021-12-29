package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupUserDTOTest implements Models {
     private static final UUID groupId = UUID.randomUUID();
     private final List<UUID> userIdList = Collections.singletonList(UUID.randomUUID());

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        GroupUserDTO dto = new GroupUserDTO(groupId, userIdList);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        GroupUserDTO dto = new GroupUserDTO();
        dto.setGroup(groupId);
        dto.setUserList(userIdList);

        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        GroupUserDTO dto = GroupUserDTO.builder().group(groupId).userList(userIdList).build();
        check(dto);
    }

    private void check(GroupUserDTO item){
        assertNotNull(item, createMsg("GroupUserDTO"));
        assertEquals(groupId, item.getGroup(), createMsg("groupId"));
        assertEquals(userIdList, item.getUserList(), createMsg("userIdList"));
        assertFalse(item.getUserList().isEmpty(), createMsg("userIdList"));
    }
}
