package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.enums.GroupEnum;
import br.com.collecion.pokemontcg.repositories.GroupRepository;
import br.com.collecion.pokemontcg.repositories.GroupUserRepository;
import br.com.collecion.pokemontcg.repositories.UserRepository;
import br.com.collecion.pokemontcg.utils.EncryptionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public User findByUUID(UUID uuid) {
        Optional<User> result = repository.findById(uuid);
        return result.isEmpty() ? null : result.get();
    }

    public User save(User entity) throws NoSuchAlgorithmException, InvalidKeySpecException {
        entity.setPassword(EncryptionManager.encript(entity.getPassword()));
        User user = repository.save(entity);

        Optional<Group> optionalGroup = groupRepository.findByName(GroupEnum.ADMIN.getText());

        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            GroupUser groupUser = new GroupUser();
            groupUser.setUser(user);
            groupUser.setGroup(group);
            groupUserRepository.save(groupUser);
        }

        return user;
    }

    public User edit(User user) {
        User userEdited = null;

        if (repository.existsById(user.getId())) {
            userEdited = repository.save(user);
        } else {
            logger.error("No user found with id {}:", user.getId());
        }
        return userEdited;
    }

    public Boolean delete(UUID id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
