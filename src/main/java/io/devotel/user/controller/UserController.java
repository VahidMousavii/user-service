package io.devotel.user.controller;

import io.devotel.common.GeneralResponseDto;
import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;
import io.devotel.user.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity<GeneralResponseDto<UserDTO>> addUser(@Valid @RequestBody AddUserDTO addUserDTO) {
        return ResponseEntity
                .status(201)
                .body(userServiceImpl.addUser(addUserDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponseDto<UserDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .ok(userServiceImpl.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<GeneralResponseDto<List<UserDTO>>> getAllUsers() {
        return ResponseEntity
                .ok(userServiceImpl.getAllUsersResponse());
    }
}
