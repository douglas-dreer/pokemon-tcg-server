package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.dtos.GroupDTO;
import br.com.collecion.pokemontcg.dtos.GroupUserDTO;
import br.com.collecion.pokemontcg.enities.Group;
import br.com.collecion.pokemontcg.enities.GroupUser;
import br.com.collecion.pokemontcg.enums.MessagesEnum;
import br.com.collecion.pokemontcg.services.GroupService;
import br.com.collecion.pokemontcg.services.GroupUserService;
import br.com.collecion.pokemontcg.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("config/groups")
public class GroupController {
    @Autowired
    private GroupService service;

    @Autowired
    private GroupUserService groupUserService;

    @GetMapping(produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<List<Group>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{uuid}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<Group> findByUUID(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.findByUUID(uuid));
    }

    @PostMapping(produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody GroupDTO group) throws URISyntaxException {
        Group enity = Converters.groupDTOoGroupEntity(group);

        enity = service.save(enity);
        group = Converters.groupEntityToGroupDTO(enity);

        URI location = URI.create(String.format("/api/v1/config/groups/%s", group.getId()));
        return enity.equals(null) ? ResponseEntity.created(location).build() : ResponseEntity.internalServerError().body(MessagesEnum.INTERNAL_ERROR);
    }

    @PutMapping(value = "/{uuid}", produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Group> edit(@PathVariable(name = "uuid") UUID uuid, @RequestBody GroupDTO group) {
        return ResponseEntity.ok(service.edit(Converters.groupDTOoGroupEntity(group)));
    }

    @DeleteMapping(value = "/{uuid}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Boolean> delete(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.delete(uuid));
    }

    @PostMapping(value = "/{groupId}/user/{userId}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<?> addUserToGroup(@PathVariable(name = "groupId") UUID groupId, @PathVariable(name = "userId") UUID userId) {
        String msgError = String.format(MessagesEnum.ERROR.getText(), "groupId", groupId, "userId", userId);

        URI location = URI.create(String.format("/api/v1/config/groups/%s/user", userId));
        boolean status = groupUserService.save(groupId, userId);
        return status ? ResponseEntity.created(location).build() : ResponseEntity.internalServerError().body(msgError);
    }

    @DeleteMapping(value = "/{groupId}/user/{userId}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<String> deleteUserFromGroup(@PathVariable(name = "groupId") UUID groupId, @PathVariable(name = "userId") UUID userId) {
        String msgError = String.format(MessagesEnum.ERROR.getText(), "groupId", groupId, "userId", userId);

        boolean status = groupUserService.removeUserFromGroup(groupId, userId);
        return status ? ResponseEntity.ok(MessagesEnum.SUCCESS.getText()) : ResponseEntity.internalServerError().body(msgError);
    }

    @GetMapping(value = "/{groupId}/user", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<GroupUserDTO> findUsersByGroupId(@PathVariable(name = "groupId") UUID groupId) {
        return ResponseEntity.ok(groupUserService.findByGroup(groupId));
    }
}
