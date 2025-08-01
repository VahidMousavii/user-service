package io.devotel.user.service;

import io.devotel.common.GeneralResponseDto;
import io.devotel.common.StaticStrings;
import io.devotel.exceptions.UserNotFoundException;
import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;
import io.devotel.user.entity.UserEntity;
import io.devotel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public GeneralResponseDto<UserDTO> addUser(AddUserDTO addUserDTO) {
        try {
            UserEntity userEntity = modelMapper.map(addUserDTO, UserEntity.class);
            UserEntity savedEntity = userRepository.save(userEntity);
            UserDTO savedDTO = modelMapper.map(savedEntity, UserDTO.class);

            return GeneralResponseDto.<UserDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message(StaticStrings.SUCCESS_MESSAGE)
                    .data(savedDTO)
                    .build();

        } catch (Exception ex) {
            log.error("Failed to save user to database", ex);

            return GeneralResponseDto.<UserDTO>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("An unexpected error occurred while saving user.")
                    .data(null)
                    .build();
        }
    }


    @Override
    public GeneralResponseDto<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userRepository.findAll()
                    .stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList());

            return GeneralResponseDto.<List<UserDTO>>builder()
                    .code(HttpStatus.OK.value())
                    .message(StaticStrings.SUCCESS_MESSAGE)
                    .data(users)
                    .build();

        } catch (Exception ex) {
            log.error("Failed to fetch all users from database", ex);

            return GeneralResponseDto.<List<UserDTO>>builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("An unexpected error occurred while retrieving users.")
                    .data(Collections.emptyList())
                    .build();
        }
    }


    @Override
    public GeneralResponseDto<UserDTO> getUserById(Long id) {
        try {
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
            return GeneralResponseDto.<UserDTO>builder()
                    .code(HttpStatus.OK.value())
                    .message(StaticStrings.SUCCESS_MESSAGE)
                    .data(userDTO)
                    .build();
        } catch (UserNotFoundException ex) {
            log.warn("User not found: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error in getUserById", ex);
            throw ex;
        }
    }
}
