package br.com.collecion.pokemontcg.dtos;

import br.com.collecion.pokemontcg.enities.User;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GroupDTO {
    private UUID id;
    private String name;
    private List<User> userList;
    private Date createAt;
    private Date updateAt;
    private Boolean status = true;
}
