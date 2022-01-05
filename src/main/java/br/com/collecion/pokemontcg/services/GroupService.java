package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository repository;

    public List<Group> findAll() {
        return (List<Group>) repository.findAll();
    }

    public Group findByUUID(UUID uuid) {
        Optional<Group> result = repository.findById(uuid);
        return result.isEmpty() ? null : result.get();
    }

    public Group findByName(String name) {
        Optional<Group> result = repository.findByName(name);
        return  result.isPresent() ? result.get() : null;
    }


    public Group save(Group group) {
        group.setStatus(true);
        group.setCreateAt(new Date());

        logger.debug(MessagesEnum.SUCCESS.getText());
        return repository.save(group);
    }

    public Group edit(Group group) {
        Group groupEdit = null;
        group.setUpdateAt(new Date());
        if (repository.existsById(group.getId())) {
            groupEdit = repository.save(group);
        } else {
            logger.error(MessagesEnum.NOT_FOUND.getText());
        }
        return groupEdit;
    }

    public Boolean delete(UUID id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
