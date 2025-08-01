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
            return GeneralResponseDto.<UserDTO>builder()
                    .code(HttpStatus.CREATED.value())
                    .message(StaticStrings.SUCCESS_MESSAGE)
                    .data(modelMapper.map(userRepository.save(modelMapper.map(addUserDTO, UserEntity.class)), UserDTO.class))
                    .build();
    }


    @Override
    public GeneralResponseDto<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = findAllUsers();
        return GeneralResponseDto.<List<UserDTO>>builder()
                .code(HttpStatus.OK.value())
                .message(StaticStrings.SUCCESS_MESSAGE)
                .data(users)
                .build();
    }

    private List<UserDTO> findAllUsers() {
        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return users;
    }


    @Override
    public GeneralResponseDto<UserDTO> getUserById(Long id) {
        UserDTO userDTO = modelMapper.map(fetchUserEntity(id), UserDTO.class);
        return GeneralResponseDto.<UserDTO>builder()
                .code(HttpStatus.OK.value())
                .message(StaticStrings.SUCCESS_MESSAGE)
                .data(userDTO)
                .build();
    }

    private UserEntity fetchUserEntity(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userEntity;
    }
}