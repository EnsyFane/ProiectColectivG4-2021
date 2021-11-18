package com.kitchen.iChef.Service;
import com.kitchen.iChef.Domain.AppUser;
import com.kitchen.iChef.Domain.Token;
import com.kitchen.iChef.Repository.UserRepository;
import com.kitchen.iChef.Service.Hashing.BCryptPasswordEncoder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    TokenService tokenService;
    @Test
    @DisplayName("Test Add Success Case")
    @Order(1)
    void add_success() {
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
        userService.addUser(appUser);
        Mockito.verify(userRepository).save(appUser);
    }
    @Test
    @DisplayName("Test Add Failure Case")
    @Order(2)
    void add_failure(){
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        Mockito.doThrow(new RuntimeException("Testing Failure Case" )).
                when(userRepository).
                save(appUser);
        Assertions.assertThrows(Exception.class, () -> {
            userService.addUser(appUser);
        });
    }
    @Test
    @DisplayName("Test Update Success Case")
    @Order(3)
    void update_success() {
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Popescu");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        userService.updateUser(appUser);
        Mockito.verify(userRepository, Mockito.only()).update(appUser);
        Mockito.verify(userRepository).update(appUser);
    }

    @Test
    @DisplayName("Test Update Failure Case")
    @Order(4)
    void update_failure() {
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        Mockito.doThrow(new RuntimeException("Testing Failure Case"))
                .when(userRepository)
                .update(appUser);
        Assertions.assertThrows(Exception.class, () -> {
            userService.updateUser(appUser);
        });
    }
    @Test
    @DisplayName("Test GetAll Success Case")
    @Order(5)
    void getAll_success() {
        List<AppUser> list = new ArrayList<>();
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        AppUser appUser1 = new AppUser();
        appUser1.setUserId("2");
        appUser1.setFirstName("Mirel");
        appUser1.setLastName("Pop");
        appUser1.setUsername("mirel.pop");
        appUser1.setEmail("mirelpop@gmail.com");
        appUser1.setJoinedDate(ZonedDateTime.now());
        appUser1.setLastOnline(ZonedDateTime.now());
        appUser1.setIsAdmin(false);
        appUser1.setHashedPassword("popcorn");
        list.add(appUser);
        list.add(appUser1);

        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<AppUser> newList = userService.getAllUsers();
        Mockito.verify(userRepository, Mockito.only()).findAll();
        Mockito.verify(userRepository).findAll();

        Assertions.assertEquals(2,newList.size());
        Assertions.assertEquals("Andrei",newList.get(0).getFirstName());
        Assertions.assertEquals("Mirel",newList.get(1).getFirstName());
    }
    @Test
    @DisplayName("test GetAll Failure Case")
    @Order(6)
    void getAll_failure() {
        Mockito.doThrow(new RuntimeException("Testing Failure Case"))
                .when(userRepository)
                .findAll();
        Assertions.assertThrows(Exception.class, () -> {
            userService.getAllUsers();
        });
    }
    @Test
    @DisplayName("Test DeleteUser Success Case")
    @Order(7)
    void deleteUser_success() {
        userService.deleteUser("1");
        Mockito.verify(userRepository, Mockito.only()).delete("1");
        Mockito.verify(userRepository).delete("1");
    }

    @Test
    @DisplayName("Test DeleteUser Failure Case")
    @Order(8)
    void deleteUser_failure() {
        Mockito.doThrow(new RuntimeException("Testing Failure Case"))
                .when(userRepository)
                .delete("1");
        Assertions.assertThrows(Exception.class, () -> {
            userService.deleteUser("1");
        });
    }
    @Order(9)
    @Test
    @DisplayName("Test Signup Success Case")
    void signup_success(){
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        Mockito.when(userRepository.findByUsername(appUser.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
        try {
            userService.signUp(appUser);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    @Order(10)
    @Test
    @DisplayName("Test Signup Failure Case")
    void signup_failure(){
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword("popcorn");
        Mockito.when(userRepository.findByUsername(appUser.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.of(appUser));
        Assertions.assertThrows(Exception.class, () -> {
            userService.signUp(appUser);
        });
    }
    @Order(11)
    @Test
    @DisplayName("Test Login Success Case")
    void login_success(){
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword(BCryptPasswordEncoder.hash("popcorn"));
        Mockito.when(userRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.of(appUser));
        try {
            userService.login(appUser.getEmail(), "popcorn");
            Mockito.verify(tokenService).generateValidToken(appUser.getUserId());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    @Order(12)
    @Test
    @DisplayName("Test Login Failure Case")
    void login_failure(){
        AppUser appUser = new AppUser();
        appUser.setUserId("1");
        appUser.setFirstName("Andrei");
        appUser.setLastName("Pop");
        appUser.setUsername("andrei.pop");
        appUser.setEmail("andreipop@gmail.com");
        appUser.setJoinedDate(ZonedDateTime.now());
        appUser.setLastOnline(ZonedDateTime.now());
        appUser.setIsAdmin(false);
        appUser.setHashedPassword(BCryptPasswordEncoder.hash("popcorn"));
        Mockito.when(userRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.of(appUser));
        try {
            userService.login(appUser.getEmail(), "wrong_password");
            Mockito.verify(tokenService).generateValidToken(appUser.getUserId());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}