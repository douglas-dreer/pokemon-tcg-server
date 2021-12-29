package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.GroupUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, UUID> {

    @Transactional
    @Modifying
    @Query("DELETE FROM GroupUser gu WHERE gu.group.id = :group AND gu.user.id = :user")
    int deleteByGroupAndUser(UUID group, UUID user);
}
