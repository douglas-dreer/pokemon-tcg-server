package br.com.collecion.pokemontcg.entities;

import br.com.collecion.pokemontcg.enities.*;
import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleGroupTest implements Models {
    private static final UUID id = UUID.randomUUID();
    private static final Role role = new Role();
    private static final Group group = new Group();
    private static final Date createAt = new Date();
    private static final Date updateAt = new Date();
    private static final boolean status = true;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        RoleGroup dto = new RoleGroup(id, group, role, createAt, updateAt, status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        RoleGroup dto = new RoleGroup();
        dto.setId(id);
        dto.setGroup(group);
        dto.setRole(role);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        dto.setStatus(status);

        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        RoleGroup dto = RoleGroup.builder()
                .id(id).group(group).role(role)
                .createAt(createAt).updateAt(updateAt)
                .status(status).build();

        check(dto);
    }

    private void check(RoleGroup item){
        assertNotNull(item, createMsg("RoleGroup"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(group, item.getGroup(), createMsg("group"));
        assertEquals(role, item.getRole(), createMsg("role"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }
}
