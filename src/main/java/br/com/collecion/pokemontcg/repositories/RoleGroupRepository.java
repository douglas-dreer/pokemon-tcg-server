package br.com.collecion.pokemontcg.repositories;

import br.com.collecion.pokemontcg.enities.RoleGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleGroupRepository extends CrudRepository<RoleGroup, UUID> {

    @Query("SELECT rg FROM RoleGroup rg WHERE rg.role.id = :roleId AND rg.group.id = :groupId")
    RoleGroup findByRoleAndGroup(@Param("roleId") UUID roleId, @Param("groupId") UUID groupId);


    @Query("SELECT rg FROM RoleGroup rg WHERE rg.role.id = :roleId")
    List<RoleGroup> findGroupByRoleId(@Param("roleId") UUID roleId);
}
