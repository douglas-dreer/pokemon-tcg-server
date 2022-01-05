package br.com.collecion.pokemontcg.entities;

import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest implements Models {
     private static final UUID id = UUID.randomUUID();
     private static final String name = "Tabitha Rowe";
     private static final String email = "Simeon65@hotmail.com";
     private static final String username = "Richard.Swaniawski53";
     private static final String password = "Emmanuel_Kilback@hotmail.com";
     private static final Date createAt = new Date();
     private static final Date updateAt = new Date();
     private static final boolean status = true;

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        User user = new User(id, name, email, username, password, createAt, updateAt, status);
        check(user);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateAt(createAt);
        user.setUpdateAt(updateAt);
        user.setStatus(status);

        check(user);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        User user = User.builder()
                .id(id).name(name).email(email)
                .username(username).password(password)
                .createAt(createAt).updateAt(updateAt)
                .status(status).build();

        check(user);
    }

    @Test
    public void mustReturnSuccess_WhenPrePersistAndPreUpdate() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.prePersist();
        user.preUpdate();
        checkPrePersistAndPreUpdate(user);
    }

    private void check(User item){
        assertNotNull(item, createMsg("User"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(email, item.getEmail(), createMsg("email"));
        assertEquals(username, item.getUsername(), createMsg("username"));
        assertEquals(password, item.getPassword(), createMsg("password"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }

    private void checkPrePersistAndPreUpdate(User item){
        assertNotNull(item, createMsg("User"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(email, item.getEmail(), createMsg("email"));
        assertEquals(username, item.getUsername(), createMsg("username"));
        assertEquals(password, item.getPassword(), createMsg("password"));
        assertNotNull(item.getCreateAt(), createMsg("prePersist"));
        assertNotNull(item.getUpdateAt(), createMsg("preUpdate"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }
}
