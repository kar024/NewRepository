package com.example.demo.LogInSignUp.Services;

import com.example.demo.LogInSignUp.persistence.models.Role;
import com.example.demo.LogInSignUp.persistence.models.User;
import com.example.demo.LogInSignUp.persistence.repositories.UserRepository;
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

@Service
public class UserService implements UserDetailsService {
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

    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User userfound;
        if (user.equals(null)) {
            userfound = new User();
        } else {
            userfound = user.get();
        }
        return userfound;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Boolean saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> userGetList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

}
