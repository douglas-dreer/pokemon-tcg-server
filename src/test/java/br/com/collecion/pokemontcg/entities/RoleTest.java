package br.com.collecion.pokemontcg.entities;

import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoleTest implements Models {
    private static final UUID id = UUID.randomUUID();
    private static final String name = "CHANGE_YOUR_OWN_PASSWORD";
    private static final Date createAt = new Date();
    private static final Date updateAt = new Date();
    private static final boolean status = true;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        Role dto = new Role(id, name, createAt, updateAt, status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        Role dto = new Role();
        dto.setId(id);
        dto.setName(name);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        dto.setStatus(status);

        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        Role dto = Role.builder()
                .id(id).name(name)
                .createAt(createAt).updateAt(updateAt)
                .status(status).build();

        check(dto);
    }

    private void check(Role item){
        assertNotNull(item, createMsg("Role"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }
}
