package br.com.collecion.pokemontcg.dtos;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GroupDTO {
    private UUID id;
    private String name;
    private Date createAt;
    private Date updateAt;
    private Boolean status = true;
}
