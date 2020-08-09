package com.savstanis.quickauction.service;

import com.savstanis.quickauction.exceptions.UserAlreadyExistAuthenticationException;
import com.savstanis.quickauction.model.Role;
import com.savstanis.quickauction.model.User;
import com.savstanis.quickauction.repository.RoleRepository;
import com.savstanis.quickauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public User register(@Valid User user) throws UserAlreadyExistAuthenticationException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistAuthenticationException("User with this username already exists!");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistAuthenticationException("User with this email already exists!");
        }

        Role role = roleRepository.findByName("USER");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roleList);

        User registeredUser = userRepository.save(user);

        return registeredUser;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        return user;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return user;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
