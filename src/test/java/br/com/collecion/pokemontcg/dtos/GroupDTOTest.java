package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GroupDTOTest implements Models {
     private static final UUID id = UUID.randomUUID();
     private static final String name = "Administrator";
     private static final Date createAt = new Date();
     private static final Date updateAt = new Date();
     private static final boolean status = true;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        GroupDTO dto = new GroupDTO(id, name, createAt, updateAt, status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        GroupDTO group = new GroupDTO();
        group.setId(id);
        group.setName(name);
        group.setCreateAt(createAt);
        group.setUpdateAt(updateAt);
        group.setStatus(status);

        check(group);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        GroupDTO group = GroupDTO.builder()
                .id(id).name(name).createAt(createAt)
                .updateAt(updateAt)
                .status(status).build();

        check(group);
    }

    private void check(GroupDTO item){
        assertNotNull(item, createMsg("Group"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }
}
