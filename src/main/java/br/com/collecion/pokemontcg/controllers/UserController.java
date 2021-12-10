package br.com.collecion.pokemontcg.controllers;

import br.com.collecion.pokemontcg.dtos.UserDTO;
import br.com.collecion.pokemontcg.enities.User;
import br.com.collecion.pokemontcg.services.UserService;
import br.com.collecion.pokemontcg.utils.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{uuid}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public ResponseEntity<User> findByUUID(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.findByUUID(uuid));
    }

    @PostMapping(produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<User> save(@RequestBody UserDTO user) throws Exception {
        return new ResponseEntity<>(service.save(Converters.userDTOoUserEntity(user)), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}", produces = {"application/json;charset=UTF-8"}, consumes = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<User> edit(@PathVariable(name = "uuid") UUID uuid, @RequestBody UserDTO user) {
        return ResponseEntity.ok(service.edit(Converters.userDTOoUserEntity(user)));
    }

    @DeleteMapping(value = "/{uuid}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseEntity<Boolean> delete(@PathVariable(name = "uuid") UUID uuid) {
        return ResponseEntity.ok(service.delete(uuid));
    }
}
