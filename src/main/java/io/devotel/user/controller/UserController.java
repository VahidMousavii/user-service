package io.devotel.user.controller;

import io.devotel.common.GeneralResponseDto;
import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;
import io.devotel.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralResponseDto<UserDTO>> addUser(@Valid @RequestBody AddUserDTO addUserDTO) {
        return ResponseEntity
                .status(201)
                .body(userService.addUser(addUserDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponseDto<UserDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<GeneralResponseDto<List<UserDTO>>> getAllUsers() {
        return ResponseEntity
                .ok(userService.getAllUsers());
    }
}
