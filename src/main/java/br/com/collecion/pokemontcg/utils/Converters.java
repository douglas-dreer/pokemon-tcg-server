package br.com.collecion.pokemontcg.utils;

import br.com.collecion.pokemontcg.dtos.UserDTO;
import br.com.collecion.pokemontcg.enities.User;


public class Converters {

    public Converters() {
    }

    public static User userDTOoUserEntity(UserDTO dto){
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .status(dto.getStatus())
                .build();
    }

    public static UserDTO userEntityToUserDTO(User entity){
        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .status(entity.getStatus())
                .build();
    }
}
