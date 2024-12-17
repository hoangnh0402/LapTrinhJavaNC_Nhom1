package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.RecordRequestDto;
import com.example.projectbase.domain.dto.response.RecordResponseDto;
import com.example.projectbase.domain.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    Record toRecord(RecordRequestDto recordRequestDto);
    @Mappings({
            @Mapping(target = "userCode", source = "record.user.username")
    })
    RecordResponseDto toRecordResponseDto(Record record);
    @Mappings({
            @Mapping(target = "userCode", source = "record.user.username")
    })
    List<RecordResponseDto> toRecordResponseDtoList(List<Record> records);
}
