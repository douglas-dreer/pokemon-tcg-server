package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.dtos.GroupRoleDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.enities.RoleGroup;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.repositories.GroupRepository;
import br.com.collecion.pokemontcg.repositories.RoleGroupRepository;
import br.com.collecion.pokemontcg.repositories.RoleRepository;
import br.com.collecion.pokemontcg.utils.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleGroupService {
    private static final Logger logger = LoggerFactory.getLogger(RoleGroupService.class);

    @Autowired
    private RoleGroupRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<RoleGroup> findAll() {
        return (List<RoleGroup>) repository.findAll();
    }

    public RoleGroup findByUUID(UUID uuid) {
        Optional<RoleGroup> result = repository.findById(uuid);
        return result.isPresent() ? result.get() : null;
    }

    public boolean save(UUID roleId, UUID groupId) {
        boolean status = false;
        RoleGroup roleGroup = new RoleGroup();
        Role role = checkExistRole(roleId);
        Group group = checkExistGroup(groupId);

        if (role != null && group != null) {
            roleGroup.setGroup(group);
            roleGroup.setRole(role);
            repository.save(roleGroup);
            status = true;
        } else {
            logger.error(MessagesEnum.NOT_FOUND.getText());
            logger.info("{'roleId': {}, 'groupId': {}", roleId, groupId);
        }
        return status;
    }

    public RoleGroup edit(RoleGroup role) {
        RoleGroup userEdited = null;

        if (repository.existsById(role.getId())) {
            userEdited = repository.save(role);
        }

        return userEdited;
    }

    public Boolean delete(UUID id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }

    private Role checkExistRole(UUID id) {
        Optional<Role> result = roleRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    private Group checkExistGroup(UUID id) {
        Optional<Group> result = groupRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public boolean removeRoleFromGroup(UUID roleId, UUID groupId) {
        boolean status = false;
        RoleGroup roleGroup = repository.findByRoleAndGroup(roleId, groupId);

        if (roleGroup != null) {
            repository.delete(roleGroup);
            status = true;
        }
        return status;
    }

    public GroupRoleDTO findGroupByRoleId(UUID roleId) {
        return Converters.roleGroupListToGroupRole(repository.findGroupByRoleId(roleId));
    }
}
