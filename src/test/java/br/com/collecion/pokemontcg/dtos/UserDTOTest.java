package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.models.Models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserDTOTest implements Models {
    private static final UUID id = UUID.randomUUID();
    private static final String name = "name";
    private static final String email = "email";
    private static final String username = "username";
    private static final String password = "password";
    private static final Date createAt = new Date();
    private static final Date updateAt = new Date();
    private static final Boolean status = true;


    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByConstructorWithParams() {
        UserDTO dto = new UserDTO(id, name, email, username, password, createAt, updateAt, status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateBySetters() {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        dto.setStatus(status);
        check(dto);
    }

    @Override
    @Test
    public void mustReturnSuccess_WhenCreateByBuilder() {
        UserDTO dto = UserDTO.builder()
                .id(id)
                .name(name)
                .email(email)
                .username(username)
                .password(password)
                .createAt(createAt)
                .updateAt(updateAt)
                .status(status)
                .build();
        check(dto);
    }

    private void check(UserDTO item){
        Assertions.assertNotNull(item, createMsg("UserDTO"));
        assertEquals(id, item.getId(), createMsg("id"));
        assertEquals(name, item.getName(), createMsg("name"));
        assertEquals(email, item.getEmail(), createMsg("email"));
        assertEquals(username, item.getUsername(), createMsg("username"));
        assertEquals(password, item.getPassword(), createMsg("password"));
        assertEquals(createAt, item.getCreateAt(), createMsg("createAt"));
        assertEquals(updateAt, item.getUpdateAt(), createMsg("updateAt"));
        assertEquals(status, item.getStatus(), createMsg("status"));
    }
}
