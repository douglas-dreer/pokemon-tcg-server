package br.com.collecion.pokemontcg.utils;

import br.com.collecion.pokemontcg.dtos.*;
import br.com.collecion.pokemontcg.enities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;


public class Converters {
    private static final Logger logger = LoggerFactory.getLogger(Converters.class);

    public static User userDTOoUserEntity(UserDTO dto) {
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

    public static UserDTO userEntityToUserDTO(User entity) {
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

    public static Group groupDTOGroupEntity(GroupDTO dto) {
        return Group.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .status(dto.getStatus())
                .build();
    }

    public static GroupDTO groupEntityToGroupDTO(Group entity) {
        return GroupDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .status(entity.getStatus())
                .build();
    }

    public static Role roleDTOtoRoleEntity(RoleDTO dto) {
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .status(dto.getStatus())
                .build();
    }

    public static RoleDTO roleEntitytoRoleDTO(Role entity) {
        return RoleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .status(entity.getStatus())
                .build();
    }

    public static GroupRoleDTO roleGroupListToGroupRole(List<RoleGroup> roleGroupList) {
        GroupRoleDTO result = new GroupRoleDTO();

        if (roleGroupList.isEmpty()) {
            return new GroupRoleDTO();
        }

        result.setRole(roleGroupList.get(0).getRole());
        List<Group> groupList = roleGroupList.stream().map(item -> item.getGroup()).collect(Collectors.toList());
        result.setGroupList(groupList);

        return result;
    }

    public static GroupUserDTO groupUserListToGroupUser(List<GroupUser> groupUserList) {
        GroupUserDTO result = new GroupUserDTO();

        result.setGroup(groupUserList.get(0).getGroup());
        List<User> userList = groupUserList.stream().map(item -> item.getUser()).collect(Collectors.toList());
        result.setUserList(userList);
        return result;
    }
}
