package br.com.collecion.pokemontcg.entities;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GroupUserTest implements Models {
     private static final UUID id = UUID.randomUUID();
     private static final User user = new User();
     private static final Group group = new Group();
     private static final Date createAt = new Date();
     private static final Date updateAt = new Date();
     private static final boolean status = true;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        GroupUser dto = new GroupUser(id, group, user, createAt, updateAt, status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        GroupUser dto = new GroupUser();
        dto.setId(id);
        dto.setGroup(group);
        dto.setUser(user);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        dto.setStatus(status);

        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        GroupUser dto = GroupUser.builder()
                .id(id).group(group).user(user)
                .createAt(createAt).updateAt(updateAt)
                .status(status).build();

        check(dto);
    }

    @Test
    public void mustReturnSuccess_WhenPrePersistAndPreUpdate() {
        GroupUser dto = new GroupUser();
        dto.setId(id);
        dto.setGroup(group);
        dto.setUser(user);
        dto.prePersist();
        dto.preUpdate();

        checkPrePersistAndPreUpdate(dto);
    }

    private void check(GroupUser item){
        assertNotNull(item, createMsg("GroupUser"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(group, item.getGroup(), createMsg("group"));
        assertEquals(user, item.getUser(), createMsg("user"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }

    private void checkPrePersistAndPreUpdate(GroupUser item){
        assertNotNull(item, createMsg("GroupUser"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(group, item.getGroup(), createMsg("group"));
        assertEquals(user, item.getUser(), createMsg("user"));
        assertNotNull(item.getCreateAt(), createMsg("prePersist"));
        assertNotNull(item.getUpdateAt(), createMsg("preUpdate"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }


}
