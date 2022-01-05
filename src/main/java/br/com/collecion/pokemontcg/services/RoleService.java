package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository repository;

    public List<Role> findAll() {
        return (List<Role>) repository.findAll();
    }

    public Role findByUUID(UUID uuid) {
        Optional<Role> result = repository.findById(uuid);
        return result.isEmpty() ? null : result.get();
    }

    public Role save(Role role) {
        return repository.save(role);
    }

    public Role edit(Role role) {
        Role userEdited = null;

        if (repository.existsById(role.getId())) {
            userEdited = repository.save(role);
        } else {
            logger.error("No role found with id {}:", role.getId());
        }
        return userEdited;
    }

    public Boolean delete(UUID id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }
}
