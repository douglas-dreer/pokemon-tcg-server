package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.repositories.UserRepository;
import br.com.collecion.pokemontcg.utils.EncryptionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public User findByUUID(UUID uuid) {
        Optional<User> result = repository.findById(uuid);
        return result.isEmpty() ? null : result.get();
    }

    public User save(User user) throws Exception {
        user.setStatus(true);
        user.setPassword(criptoPassword(user.getPassword()));
        user.setCreateAt(new Date());
        logger.debug("User created successfully. {}", user);
        return repository.save(user);
    }

    public User edit(User user) {
        User userEdited = null;
        user.setUpdateAt(new Date());
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

    private String criptoPassword(String password) throws Exception {
        return EncryptionManager.encript(password);
    }
}
