package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupRoleDTO {
    private Role role;
    private List<Group> groupList;
}
