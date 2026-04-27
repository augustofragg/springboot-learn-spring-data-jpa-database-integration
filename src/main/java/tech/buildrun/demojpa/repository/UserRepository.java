package tech.buildrun.demojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.demojpa.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
