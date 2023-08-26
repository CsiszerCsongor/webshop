package cubix.kh.exam.userservice.entity;

import cubix.kh.exam.userservice.enums.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles", schema = CommonDatabaseConstants.USERS_WEBSHOP_SCHEMA_NAME)
public class RolesEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_webshop_roles")
    @SequenceGenerator(name = "seq_user_webshop_roles", allocationSize = 1, schema = CommonDatabaseConstants.USERS_WEBSHOP_SCHEMA_NAME)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private Boolean usable;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

}
