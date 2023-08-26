package cubix.kh.exam.userservice.repository;

import cubix.kh.exam.userservice.entity.RolesEntity;
import cubix.kh.exam.userservice.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByRole(RoleEnum role);

}
