package com.fpmislata.daw1.projectedaw1.integration.domain.service;

import com.fpmislata.daw1.projectedaw1.data.UserData;
import com.fpmislata.daw1.projectedaw1.domain.entity.User;
import com.fpmislata.daw1.projectedaw1.domain.service.UserService;
import com.fpmislata.daw1.projectedaw1.domain.service.impl.UserServiceImpl;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.UserDaoMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.UserTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMemoryDaoTest {
    private final UserTableMemory userTableMemory = Mockito.mock(UserTableMemory.class);

    private final UserService userService = new UserServiceImpl(
            new UserRepositoryImpl(
                    new UserDaoMemory(userTableMemory)
            )
    );

    private final List<User> USER_LIST = UserData.USER_LIST;

    @BeforeEach
    void setup() {
        when(userTableMemory.get()).thenReturn(UserData.USER_RECORD_LIST);
    }

    @Nested
    class FindByUsername {
        @Test
        void givenUsername_shouldReturnUser() {
            User expectedUser = USER_LIST.get(0);
            User result = userService.findByUsername("admin");
            assertEquals(expectedUser, result);
        }

        @Test
        void givenDifferentUsername_shouldReturnDifferentUser() {
            User expectedUser = USER_LIST.get(1);
            User result = userService.findByUsername("user");
            assertEquals(expectedUser, result);
        }

        @Test
        void givenNonExistentUsername_shouldReturnNull() {
            User result = userService.findByUsername("nonExistentUsername");
            assertNull(result);
        }
    }

    @Nested
    class FindByEmail {
        @Test
        void givenEmail_shouldReturnUser() {
            User expectedUser = USER_LIST.get(0);
            User result = userService.findByEmail("admin@localhost");
            assertEquals(expectedUser, result);
        }

        @Test
        void givenDifferentEmail_shouldReturnDifferentUser() {
            User expectedUser = USER_LIST.get(1);
            User result = userService.findByEmail("user@localhost");
            assertEquals(expectedUser, result);
        }

        @Test
        void givenNonExistentEmail_shouldReturnNull() {
            User result = userService.findByEmail("nonExistentEmail");
            assertNull(result);
        }
    }

}