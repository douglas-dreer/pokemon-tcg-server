package br.com.collecion.pokemontcg.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleDTO {
    private UUID id;
    private String name;
    private Date createAt;
    private Date updateAt;
    private Boolean status = true;
}
