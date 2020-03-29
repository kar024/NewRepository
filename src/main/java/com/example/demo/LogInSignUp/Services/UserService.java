package com.example.demo.LogInSignUp.Services;

import com.example.demo.LogInSignUp.Rest.Controllers.RegistrationController;
import com.example.demo.LogInSignUp.Rest.Models.UserRequestModel;
import com.example.demo.LogInSignUp.Rest.Models.UserResponseModel;
import com.example.demo.LogInSignUp.persistence.models.Role;
import com.example.demo.LogInSignUp.persistence.models.User;
import com.example.demo.LogInSignUp.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user.equals(null)) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public UserResponseModel findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User userfound;
        if (user.equals(null)) {
            userfound = new User();
        } else {
            userfound = user.get();
        }
        return buildUserResponseModelFromUser(userfound);
    }

    public List<UserResponseModel> allUsers() {
        final List<User> userList = userRepository.findAll();
        return userList.stream()
                .map((each -> buildUserResponseModelFromUser(each))).collect(Collectors.toList());
    }

    public Boolean saveUser(UserRequestModel user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User newUser = buildUserFromUserRequestModel(user);

        userRepository.save(newUser);

        return true;
    }

    public Boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    public User buildUserFromUserRequestModel(UserRequestModel userRequestModel) {
        User user = new User();
        user.setPasswordConfirm(userRequestModel.getPasswordConfirm());
        user.setPassword(user.getPassword());
        user.setUsername(userRequestModel.getUsername());
        user.setRoles(userRequestModel.getRoles());
        return user;
    }

    public UserResponseModel buildUserResponseModelFromUser(User user) {
        UserResponseModel userResponseModel = new UserResponseModel(user.getId(), user.getPassword(), user.getUsername(), user.getRoles());
        return userResponseModel;
    }
}
