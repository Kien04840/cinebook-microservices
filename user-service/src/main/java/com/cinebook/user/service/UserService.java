package com.cinebook.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import com.cinebook.user.dto.request.CreateUserRequest;
import com.cinebook.user.entity.User;
import com.cinebook.user.repository.UserRepository;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Lombok annotation để tự tạo constructor cho các trường final
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    public User createUser(CreateUserRequest request) {
        // Logic kiểm tra email đã tồn tại
        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email " + request.getEmail() + " đã được sử dụng.");
        });

        // Tạo một đối tượng User mới để đảm bảo chỉ lưu các trường cần thiết
        // và bỏ qua các trường do hệ thống quản lý như id, createdAt
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString()); // Luôn tạo ID mới
        newUser.setEmail(request.getEmail());
        newUser.setFullName(request.getFullName());
        newUser.setPhone(request.getPhone());

        // Mã hóa mật khẩu trước khi lưu
        newUser.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }
}
