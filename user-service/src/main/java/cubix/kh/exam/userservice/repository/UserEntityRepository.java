package cubix.kh.exam.userservice.repository;

import cubix.kh.exam.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByFacebookId(String facebookId);

    @EntityGraph(UserEntity.USER_WITH_ROLES_GRAPH)
    Optional<UserEntity> findIdentityByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
