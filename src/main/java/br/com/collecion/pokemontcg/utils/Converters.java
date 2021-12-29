package br.com.collecion.pokemontcg.utils;

import br.com.collecion.pokemontcg.dtos.GroupDTO;
import br.com.collecion.pokemontcg.dtos.GroupUserDTO;
import br.com.collecion.pokemontcg.dtos.UserDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class Converters {
    private static final Logger logger = LoggerFactory.getLogger(Converters.class);

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

    public static Group groupDTOoGroupEntity(GroupDTO dto){
        return Group.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .status(dto.getStatus())
                .build();
    }

    public static GroupDTO groupEntityToGroupDTO(Group entity){
        return GroupDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .status(entity.getStatus())
                .build();
    }

    public static GroupUserDTO groupUserToList(List<GroupUser> groupUserList) {
        GroupUserDTO result = new GroupUserDTO();

        result.setGroup(groupUserList.get(0).getId());
        List<UUID> userList = groupUserList.stream().map(item -> item.getUser().getId()).collect(Collectors.toList());
        result.setUserList(userList);

        return result;
    }
}
