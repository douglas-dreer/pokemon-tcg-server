package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoleDTOTest implements Models {
    private static final UUID id = UUID.randomUUID();
    private static final String name = "Admin";   
    private static final Date createAt = new Date();
    private static final Date updateAt = new Date();
    private static final boolean status = true;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        RoleDTO dto = new RoleDTO(id, name, createAt, updateAt, status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        RoleDTO dto = new RoleDTO();
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
        RoleDTO dto = RoleDTO.builder()
                .id(id).name(name)
                .createAt(createAt).updateAt(updateAt)
                .status(status)
                .build();
        check(dto);
    }

    private void check(RoleDTO item){
        assertNotNull(item, createMsg("RoleDTO"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }
}
