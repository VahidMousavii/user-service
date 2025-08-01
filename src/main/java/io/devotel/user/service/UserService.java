package io.devotel.user.service;

import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO addUser(AddUserDTO addUserDTO);

    UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();
}
