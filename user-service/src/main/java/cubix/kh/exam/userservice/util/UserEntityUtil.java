package cubix.kh.exam.userservice.util;

import cubix.kh.exam.userservice.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class UserEntityUtil {

    public static Set<String> getUserRoles(final UserEntity userEntity){
        return userEntity.getRoles()
                         .stream()
                         .map(rolesEntity -> rolesEntity.getRole().name())
                         .collect(Collectors.toUnmodifiableSet());
    }
}
