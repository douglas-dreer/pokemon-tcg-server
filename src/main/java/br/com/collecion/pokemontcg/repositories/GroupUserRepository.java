package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.GroupUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, UUID> {
    @Query("SELECT gu FROM GroupUser gu WHERE gu.group.id = :groupId")
    List<GroupUser> findUsersByGroupId(@Param("groupId") UUID groupId);

    @Query("SELECT gu FROM GroupUser gu WHERE gu.group.id = :groupId AND gu.user.id = :userId")
    GroupUser findByUserAndGroup(@Param("userId") UUID userId, @Param("groupId") UUID groupId);
}
