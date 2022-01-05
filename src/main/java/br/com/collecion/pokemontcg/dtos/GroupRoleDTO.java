package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.Role;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GroupRoleDTO {
    private Role role;
    private List<Group> groupList;
}
