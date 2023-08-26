package cubix.kh.exam.userservice.mapper;

import cubix.kh.exam.userservice.dto.RegisterResponseDTO;
import cubix.kh.exam.userservice.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterResponseDTO registeredUserToDTO(UserEntity user);
}
