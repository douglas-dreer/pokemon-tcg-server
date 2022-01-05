package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.dtos.GroupUserDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.repositories.GroupUserRepository;
import br.com.collecion.pokemontcg.utils.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupUserService {
    private static final Logger logger = LoggerFactory.getLogger(GroupUserService.class);
    @Autowired
    private GroupUserRepository repository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    public boolean save(UUID groupId, UUID userId) {
        boolean status = false;
        GroupUser groupUser = new GroupUser();
        User user = checkExistUser(userId);
        Group group = checkExistGroup(groupId);

        if (user != null && group != null) {
            groupUser.setGroup(group);
            groupUser.setUser(user);

            repository.save(groupUser);
            status = true;
        } else {
            logger.error(MessagesEnum.NOT_FOUND.getText());
            logger.info("{'groupId': {}, 'userId': {}", groupId, userId);
        }
        return status;
    }

    private User checkExistUser(UUID userId) {
        return userService.findByUUID(userId);
    }

    private Group checkExistGroup(UUID groupId) {
        return groupService.findByUUID(groupId);
    }

    public boolean removeUserFromGroup(UUID groupId, UUID userId) {
        boolean status = false;
        GroupUser groupUser = repository.findByUserAndGroup(userId, groupId);

        if (groupUser != null) {
            repository.delete(groupUser);
            status = true;
        }
        return status;
    }

    public GroupUserDTO findByGroup(UUID groupId) {
        List<GroupUser> results = repository.findUsersByGroupId(groupId);
        return Converters.groupUserListToGroupUser(results);
    }
}
