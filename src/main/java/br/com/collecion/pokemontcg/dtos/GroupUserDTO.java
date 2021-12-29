package br.com.collecion.pokemontcg.dtos;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GroupUserDTO {
    private UUID group;
    private List<UUID> userList;
}
