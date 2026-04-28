package tech.buildrun.demojpa.service;

import org.springframework.stereotype.Service;
import tech.buildrun.demojpa.controller.dto.CreateUserDto;
import tech.buildrun.demojpa.entity.UserEntity;
import tech.buildrun.demojpa.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(CreateUserDto dto) {

        UserEntity user = new UserEntity();
        user.setName(dto.name());
        user.setAge(dto.age());
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public List<UserEntity> findAll() {

        return userRepository.findAll();

    }
}
