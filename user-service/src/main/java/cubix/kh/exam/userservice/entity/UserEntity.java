package cubix.kh.exam.userservice.entity;

import cubix.kh.exam.userservice.enums.UserStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NamedEntityGraph(name = UserEntity.USER_WITH_ROLES_GRAPH, attributeNodes = {
        @NamedAttributeNode(value = "roles")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = CommonDatabaseConstants.USERS_WEBSHOP_SCHEMA_NAME)
public class UserEntity {
    public static final String USER_WITH_ROLES_GRAPH = "user-with-roles";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_webshop_user")
    @SequenceGenerator(name = "seq_user_webshop_user",
            allocationSize = 1,
            schema = CommonDatabaseConstants.USERS_WEBSHOP_SCHEMA_NAME)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 300)
    private String password;

    @Column(nullable = false, length = 300)
    private String facebookId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @ManyToMany
    @JoinTable(name = "users_roles",
            schema = "user_webshop",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RolesEntity> roles;

}
