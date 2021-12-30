package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.dtos.UserDetailsDTO;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public User findByUUID(UUID uuid) {
        Optional<User> result = repository.findById(uuid);
        return result.isEmpty() ? null : result.get();
    }

    public User save(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        user.setStatus(true);
        user.setPassword(criptoPassword(user.getPassword()));
        user.setCreateAt(new Date());
        logger.debug("User created successfully. {}", user);
        return repository.save(user);
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

    private String criptoPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return encoder.encode(password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByLogin(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(MessagesEnum.NOT_FOUND.getText());
        }

        return new UserDetailsDTO(user);
    }
}
