package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}
