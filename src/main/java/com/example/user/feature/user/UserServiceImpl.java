package com.example.user.feature.user;

import com.example.user.domain.AccType;
import com.example.user.domain.Role;
import com.example.user.domain.User;
import com.example.user.feature.cv.UserDetailSpecification;
import com.example.user.feature.repo.AccTypeRepository;
import com.example.user.feature.repo.RoleRepository;
import com.example.user.feature.user.dto.*;
import com.example.user.mapper.UserMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AccTypeRepository accTypeRepository;
    private final UserMapper userMapper;

    @Override
    public CustomPage<UserResponse> getAllUsers(int page, int size, String baseUrl) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> users = userRepository.findAll(pageable);
        return CustomPagination(users.map(userMapper::toUserResponse), baseUrl);
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
        user.setAccType(accType);
        user.setIsDisabled(false);
        user.setIsVerified(false);
        user.setIsCredentialsExpired(true);
        user.setIsAccountExpired(true);
        user.setIsAccountLocked(true);
        user.setIsAdmin(false);
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
        user.setCoverUrl(userRequest.coverUrl());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(String id) {
    userRepository.deleteById(id);
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
        user.setIsDisabled(false);
        user.setIsCredentialsExpired(true);
        user.setIsAccountExpired(true);
        user.setIsAccountLocked(true);
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

    public CustomPage<UserResponse> CustomPagination(Page<UserResponse> page, String baseUrl){
        CustomPage<UserResponse> customPage = new CustomPage<>();
        if(page.hasNext()){
            customPage.setNext(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize());
        }
        if (page.hasPrevious()){
            customPage.setPrevious(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize());
        }
        customPage.setTotal((int) page.getTotalElements());
        customPage.setResults(page.getContent());
        return customPage;
    }
}
