package io.devotel.user.service;

import io.devotel.common.GeneralResponseDto;
import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;

import java.util.List;

public interface UserService {

    GeneralResponseDto<UserDTO> addUser(AddUserDTO addUserDTO);

    GeneralResponseDto<UserDTO> getUserById(Long id);

    GeneralResponseDto<List<UserDTO>> getAllUsers();
}
