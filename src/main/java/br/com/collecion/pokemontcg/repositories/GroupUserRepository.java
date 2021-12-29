package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.GroupUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, UUID> {

    @Transactional
    @Modifying
    @Query("DELETE FROM GroupUser gu WHERE gu.group.id = :groupId AND gu.user.id = :userId")
    int deleteByGroupAndUser(UUID groupId, UUID userId);

    @Query("SELECT gu FROM GroupUser gu WHERE gu.group.id = :groupId")
    List<GroupUser> findUsersByGroupId(UUID groupId);
}
