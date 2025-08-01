package io.devotel.user.service;

import io.devotel.exceptions.UserNotFoundException;
import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;
import io.devotel.user.entity.UserEntity;
import io.devotel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public UserDTO addUser(AddUserDTO addUserDTO) {
        UserEntity userEntity = modelMapper.map(addUserDTO, UserEntity.class);
        UserEntity savedEntity = userRepository.save(userEntity);
        return modelMapper.map(savedEntity, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
