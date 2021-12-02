package br.com.collecion.pokemontcg.services;

import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public User findByUUID(UUID uuid) {
        Optional<User> result = repository.findById(uuid);
        return result.isEmpty() ? null : result.get();
    }

    public User save(User user) {
        user.setStatus(true);
        user.setCreateAt(new Date());
        return repository.save(user);
    }

    public User edit(User user) {
        user.setUpdateAt(new Date());
        return repository.save(user);
    }

    public Boolean delete(UUID id) {
        repository.deleteById(id);
        return !repository.existsById(id);
    }


}
