package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends CrudRepository<Group, UUID> {
    @Query("SELECT g FROM Group g WHERE g.name = :name")
    Optional<Group> findByName(@Param("name") String name);
}
