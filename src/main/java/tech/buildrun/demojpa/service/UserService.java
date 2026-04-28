package tech.buildrun.demojpa.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.buildrun.demojpa.controller.dto.CreateUserDto;
import tech.buildrun.demojpa.controller.dto.UpdateUserDto;
import tech.buildrun.demojpa.entity.UserEntity;
import tech.buildrun.demojpa.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

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

    public Optional<UserEntity> findById(Long userId) {

        return userRepository.findById(userId);
    }

    public Optional<UserEntity> updateUserById(Long userId, UpdateUserDto dto) {

        Optional<UserEntity> user = findById(userId);

        if(user.isPresent()) {
            updateFields(dto, user);

            userRepository.save(user.get());
        }

        return user;
    }

    private static void updateFields(UpdateUserDto dto, Optional<UserEntity> user) {
        if(StringUtils.hasText(dto.name())) {
            user.get().setName(dto.name());
        }

        if(!isNull(dto.age())) {
            user.get().setAge(dto.age());
        }
    }

    public boolean deleteById(Long userId) {

        boolean exist = userRepository.existsById(userId);

        if(exist) {
            userRepository.deleteById(userId);
        }

        return exist;
    }
}



