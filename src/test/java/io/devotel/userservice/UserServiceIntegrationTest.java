package io.devotel.userservice;

import io.devotel.user.dto.AddUserDTO;
import io.devotel.user.dto.UserDTO;
import io.devotel.user.entity.UserEntity;
import io.devotel.user.repository.UserRepository;
import io.devotel.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testAddUser_shouldPersistToDatabase() {

        AddUserDTO dto = new AddUserDTO("Test User", "test@example.com");

        UserDTO savedUser = userService.addUser(dto);

        Optional<UserEntity> fromDb = userRepository.findById(Long.valueOf(savedUser.getId()));

        assertTrue(fromDb.isPresent());
        assertEquals("Test User", fromDb.get().getName());
        assertEquals("test@example.com", fromDb.get().getEmail());
    }
}
