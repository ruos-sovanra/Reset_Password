package com.example.user.feature.user;

import com.example.user.domain.AccType;
import com.example.user.domain.Role;
import com.example.user.domain.User;
import com.example.user.feature.repo.AccTypeRepository;
import com.example.user.feature.repo.RoleRepository;
import com.example.user.feature.user.dto.*;
import com.example.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccTypeRepository accTypeRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        User user = userMapper.requestToUserResponse(userRequest);

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
        AccType accType = accTypeRepository.findByName("ALUMNI")
                .orElseThrow(() -> new NoSuchElementException("AccType not found"));

        user.setRole(userRole);
        user.setCreatedAt(LocalDate.now().atStartOfDay());
        user.setUpdatedAt(LocalDate.now().atStartOfDay());
        user.setAccType(accType);
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.password()));
        user.setConfirmPassword(new BCryptPasswordEncoder().encode(userRequest.confirmPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setAvatar(userRequest.avatar());
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setPhone(userRequest.phone());
        user.setUsername(userRequest.username());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse deleteUser(String id) {

        var user = userRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("User not found"));

        userRepository.delete(user);

        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse updateProfile(String id, ProfileUpdateRequest profileUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        Role userRole = roleRepository.findByName(profileUpdateRequest.roleName())
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
        user.setRole(userRole);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse isVerified(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setIsVerified(true); // Set is_verified to true

        // Check the account type and set the role accordingly
        if (user.getAccType().getName().equalsIgnoreCase("ALUMNI")) {
            Role alumniRole = roleRepository.findByName("ALUMNI")
                    .orElseThrow(() -> new NoSuchElementException("Role not found"));
            user.setRole(alumniRole);
        }

        User updatedUser = userRepository.save(user); // Save the updated user
        return userMapper.toUserResponse(updatedUser); // Map the updated user to UserResponse
    }

    @Override
    public UserResponse isDisabled(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setIsDisabled(true); // Set is_disabled to true
        User disabledUser = userRepository.save(user); // Save the updated user
        return userMapper.toUserResponse(disabledUser);
    }

    @Override
    public UserResponse createUsers(CreateUserRequest userRequest) {
        User user = userMapper.createToUserResponse(userRequest);
        Role userRole = roleRepository.findByName(userRequest.roleName())
                .orElseThrow(() -> new NoSuchElementException("Role not found"));
        AccType accType = accTypeRepository.findByName(userRequest.AccTypeName())
                .orElseThrow(() -> new NoSuchElementException("AccType not found"));
        user.setRole(userRole);
        user.setAccType(accType);
        user.setIsVerified(true);
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.password()));
        user.setConfirmPassword(new BCryptPasswordEncoder().encode(userRequest.confirmPassword()));

        // Set isAdmin to true if the role is ADMIN
        if (userRequest.roleName().equalsIgnoreCase("ADMIN")) {
            user.setIsAdmin(true);
        }
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsersByIsVerify() {
        List<User> users = userRepository.findByIsVerified(false);
        return users.stream().map(userMapper::toUserResponse).toList();
    }
}
