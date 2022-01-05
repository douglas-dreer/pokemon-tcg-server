package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {
}
