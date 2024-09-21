package com.spring.ums.service;

import com.spring.ums.dto.UserRegisterDto;
import com.spring.ums.entity.User;
import com.spring.ums.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Dipendenza simulata

    @Mock
    private PasswordEncoder passwordEncoder;  // Dipendenza simulata per la gestione delle password

    @InjectMocks
    private UserServiceImpl userService;  // Implementazione reale del servizio da testare

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inizializza i mock
    }

    @Test
    void save_ShouldReturnSavedUser() {
        UserRegisterDto userRegDto = new UserRegisterDto();
        userRegDto.setEmail("test@example.com");
        userRegDto.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(userRegDto);

        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).save(any(User.class));
    }


}
