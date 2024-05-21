package ru.aig.SpringDB.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aig.SpringDB.entities.Role;
import ru.aig.SpringDB.repositories.RoleRepository;
import ru.aig.SpringDB.services.RoleService;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity<?> save(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(roleService.save(role));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
