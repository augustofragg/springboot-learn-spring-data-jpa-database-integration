package tech.buildrun.demojpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tech.buildrun.demojpa.controller.dto.CreateUserDto;
import tech.buildrun.demojpa.controller.dto.UpdateUserDto;
import tech.buildrun.demojpa.entity.UserEntity;
import tech.buildrun.demojpa.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.*;

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

    public Page<UserEntity> findAll(Integer page, Integer pageSize, String orderBy, String name, Long age) {

        PageRequest pageRequest = getPageRequest(page, pageSize, orderBy);

        return findWithFilters(name, age, pageRequest);
    }

    private Page<UserEntity> findWithFilters(String name, Long age, PageRequest pageRequest) {
        if(hasText(name) && !isNull(age)) {

            return userRepository.findByNameAndAgeGreaterThanEqual(name, age, pageRequest);
        }

        if(hasText(name)) {
            return userRepository.findByName(name, pageRequest);
        }

        if(!isNull(age)) {
            return userRepository.findByAgeGreaterThanEqual(age, pageRequest);
        }

        return userRepository.findAll(pageRequest);
    }

    private PageRequest getPageRequest(Integer page, Integer pageSize, String orderBy) {
        Sort.Direction direction = Sort.Direction.DESC;
        if(orderBy.equalsIgnoreCase("asc")) {
            direction = Sort.Direction.ASC;
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize,direction,"createdAt");
        return pageRequest;
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
        if(hasText(dto.name())) {
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



