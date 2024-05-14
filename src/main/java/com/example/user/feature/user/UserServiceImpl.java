package com.example.user.feature.user;

import com.example.user.domain.User;
import com.example.user.feature.user.dto.UserRequest;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
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
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toUserResponse(userRequest);
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.password()));
        user.setConfirm_password(new BCryptPasswordEncoder().encode(userRequest.confirm_password()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(String id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
        user.setAvatar(userRequest.avatar());
        user.setEmail(userRequest.email());
        user.setFirst_name(userRequest.first_name());
        user.setLast_name(userRequest.last_name());
        user.setPassword(userRequest.password());
        user.setPhone(userRequest.phone());
        user.setUsername(userRequest.username());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(String id) {
    userRepository.deleteById(id);
    }
}
