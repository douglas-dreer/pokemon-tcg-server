package br.com.collecion.pokemontcg.Utils;

import br.com.collecion.pokemontcg.dtos.GroupRoleDTO;
import br.com.collecion.pokemontcg.enities.RoleGroup;
import br.com.collecion.pokemontcg.utils.Converters;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConvertersTest {

    @Test
    public void mustReturnEmptyWhenroleGroupListToGroupRole(){
        List<RoleGroup> list = new ArrayList<>();
        GroupRoleDTO result = Converters.roleGroupListToGroupRole(list);
        assertNotNull(result);
    }
}
