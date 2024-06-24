package cs.vsu.event_ease.backend.dto.mapper;

import cs.vsu.event_ease.backend.domain.CloseEvent;
import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.dto.CloseEventDto;
import cs.vsu.event_ease.backend.dto.OpenEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CloseEventMapper {
    CloseEventMapper INSTANCE = Mappers.getMapper(CloseEventMapper.class);

    @Mapping(source = "name", target = "name")
    CloseEventDto toDto(CloseEvent openEvent);
}
