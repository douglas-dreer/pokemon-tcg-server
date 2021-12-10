package br.com.collecion.pokemontcg.dtos;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Date createAt;
    private Date updateAt;
    private Boolean status = true;
}
