package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.dtos.GroupRoleDTO;
import br.com.collecion.pokemontcg.dtos.RoleDTO;
import br.com.collecion.pokemontcg.enities.Role;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.services.GroupUserService;
import br.com.collecion.pokemontcg.services.RoleGroupService;
import br.com.collecion.pokemontcg.services.RoleService;
import br.com.collecion.pokemontcg.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("config/roles")
public class RoleController {
    @Autowired
    private RoleService service;

    @Autowired
    private RoleGroupService roleGroupService;

    @Autowired
    private GroupUserService groupUserService;

    @GetMapping(produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<List<Role>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{uuid}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<Role> findByUUID(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.findByUUID(uuid));
    }

    @PostMapping(produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody RoleDTO role) {
        Role enity = Converters.roleDTOtoRoleEntity(role);

        Optional<Role> roleOptional = Optional.ofNullable(service.save(enity));

        if (roleOptional.isPresent()) {
            role = Converters.roleEntitytoRoleDTO(roleOptional.get());
            URI location = URI.create(String.format("/api/v1/config/roles/%s", role.getId()));
            return ResponseEntity.created(location).build();
        } else  {
            return ResponseEntity.internalServerError().body(MessagesEnum.INTERNAL_ERROR.getText());
        }
    }

    @PutMapping(value = "/{uuid}", produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Role> edit(@PathVariable(name = "uuid") UUID uuid, @RequestBody RoleDTO role) {
        return ResponseEntity.ok(service.edit(Converters.roleDTOtoRoleEntity(role)));
    }

    @DeleteMapping(value = "/{uuid}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Boolean> delete(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.delete(uuid));
    }


    @PostMapping(value = "/{roleId}/group/{groupId}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<?> addRoleToGroup(@PathVariable(name = "roleId") UUID roleId, @PathVariable(name = "groupId") UUID groupId) {
        String msgError = String.format(MessagesEnum.ERROR.getText(), "roleId", roleId, "groupId", groupId);

        URI location = URI.create(String.format("/api/v1/config/roles/%s/group", roleId));
        boolean status = roleGroupService.save(roleId, groupId);
        return status ? ResponseEntity.created(location).build() : ResponseEntity.internalServerError().body(msgError);
    }


    @DeleteMapping(value = "/{roleId}/group/{groupId}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<String> deleteRoleFromGroup(@PathVariable(name = "roleId") UUID roleId, @PathVariable(name = "groupId") UUID groupId) {
        String msgError = String.format(MessagesEnum.ERROR.getText(), "roleId", roleId, "groupId", groupId);

        boolean status = roleGroupService.removeRoleFromGroup(roleId, groupId);
        return status ? ResponseEntity.ok(MessagesEnum.SUCCESS.getText()) : ResponseEntity.internalServerError().body(msgError);
    }

    @GetMapping(value = "/{roleId}/groups", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<GroupRoleDTO> findGroupsByRoleId(@PathVariable(name = "roleId") UUID roleId) {
        return ResponseEntity.ok(roleGroupService.findGroupByRoleId(roleId));
    }
}
