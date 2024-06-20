package cs.vsu.event_ease.backend.dto.mapper;

import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.domain.User;
import cs.vsu.event_ease.backend.dto.OpenEventDto;
import cs.vsu.event_ease.backend.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpenEventMapper {

    OpenEventMapper INSTANCE = Mappers.getMapper(OpenEventMapper.class);

    @Mapping(source = "name", target = "name")
    OpenEventDto toDto(OpenEvent openEvent);
}
