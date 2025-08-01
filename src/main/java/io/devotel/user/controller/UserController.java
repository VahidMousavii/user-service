package io.devotel.user.controller;

import io.devotel.common.GeneralResponseDto;
import io.devotel.common.StaticStrings;
import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;
import io.devotel.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
                .status(HttpStatus.CREATED)
                .body(GeneralResponseDto.<UserDTO>builder()
                        .code(HttpStatus.CREATED.value())
                        .message(StaticStrings.SUCCESS_MESSAGE)
                        .data(userService.addUser(addUserDTO))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponseDto<UserDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
                GeneralResponseDto.<UserDTO>builder()
                        .code(HttpStatus.OK.value())
                        .message(StaticStrings.SUCCESS_MESSAGE)
                        .data(userService.getUserById(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<GeneralResponseDto<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(
                GeneralResponseDto.<List<UserDTO>>builder()
                        .code(HttpStatus.OK.value())
                        .message(StaticStrings.SUCCESS_MESSAGE)
                        .data( userService.getAllUsers())
                        .build());
    }
}
