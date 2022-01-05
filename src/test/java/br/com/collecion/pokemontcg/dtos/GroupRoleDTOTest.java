package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GroupRoleDTOTest implements Models {
    private Role role = new Role();
    private List<Group> groupList = Collections.singletonList(new Group());

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        GroupRoleDTO dto = new GroupRoleDTO(role, groupList);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        GroupRoleDTO group = new GroupRoleDTO();
        group.setRole(role);
        group.setGroupList(groupList);
        check(group);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        GroupRoleDTO group = GroupRoleDTO.builder()
                .role(role)
                .groupList(groupList)
                .build();

        check(group);
    }

    private void check(GroupRoleDTO item){
        assertNotNull(item, createMsg("Group"));
        assertEquals(role, item.getRole(), createMsg("role"));
        assertEquals(groupList, item.getGroupList(), createMsg("groupList"));
        assertFalse(item.getGroupList().isEmpty());
    }

}
