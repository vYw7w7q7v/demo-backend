package cs.vsu.event_ease.backend.dto.mapper;

import cs.vsu.event_ease.backend.domain.Invitation;
import cs.vsu.event_ease.backend.domain.OpenEvent;
import cs.vsu.event_ease.backend.dto.InvitationDto;
import cs.vsu.event_ease.backend.dto.OpenEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvitationMapper {
    InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);
    InvitationDto toDto(Invitation invitation);
}
