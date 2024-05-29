package cs.vsu.event_ease.backend.dto.mapper;

import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "name", target = "name")
    UserDto toDto(User user);

}
