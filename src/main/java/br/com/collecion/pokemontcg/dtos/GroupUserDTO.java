package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.User;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupUserDTO {
    private Group group;
    private List<User> userList;
}
