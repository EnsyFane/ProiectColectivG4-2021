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

    AppUser createUser(String userId, String firstName,String lastName, String username, String email, ZonedDateTime joinedDate, ZonedDateTime lastOnline, Boolean isAdmin, String hashedPassword)
    {
        AppUser appUser = new AppUser();
        appUser.setUserId(userId);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setJoinedDate(joinedDate);
        appUser.setLastOnline(lastOnline);
        appUser.setIsAdmin(isAdmin);
        appUser.setHashedPassword(hashedPassword);
        return appUser;
    }

    @Test
    @DisplayName("Test Add Success Case")
    @Order(1)
    void add_success() {
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
        Mockito.when(userRepository.save(appUser)).thenReturn(appUser);
        userService.addUser(appUser);
        Mockito.verify(userRepository).save(appUser);
    }
    @Test
    @DisplayName("Test Add Failure Case")
    @Order(2)
    void add_failure(){
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
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
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
        userService.updateUser(appUser);
        Mockito.verify(userRepository, Mockito.only()).update(appUser);
        Mockito.verify(userRepository).update(appUser);
    }

    @Test
    @DisplayName("Test Update Failure Case")
    @Order(4)
    void update_failure() {
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
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
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
        AppUser appUser1= createUser("1","Mirel", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
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
    @DisplayName("Test GetAll Failure Case")
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
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
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
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
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
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
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
        AppUser appUser = createUser("1","Andrei", "Pop", "andrei.pop","andreipop@gmail.com",ZonedDateTime.now(),ZonedDateTime.now(),false,"popcorn");
        Mockito.when(userRepository.findByEmail(appUser.getEmail())).thenReturn(Optional.of(appUser));
        try {
            userService.login(appUser.getEmail(), "wrong_password");
            Mockito.verify(tokenService).generateValidToken(appUser.getUserId());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}