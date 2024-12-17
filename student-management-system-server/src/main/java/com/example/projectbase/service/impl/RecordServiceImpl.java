package com.example.projectbase.service.impl;

import com.example.projectbase.constant.CommonConstant;
import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.RateConstant;
import com.example.projectbase.domain.dto.request.RecordRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.RecordResponseDto;
import com.example.projectbase.domain.entity.Record;
import com.example.projectbase.domain.mapper.RecordMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.RecordRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final RecordMapper recordMapper;
    @Override
    public RecordResponseDto createRecord(RecordRequestDto recordRequestDto) {
        Record record = recordMapper.toRecord(recordRequestDto);
        record.setGPA((double) record.getTotalScore() /record.getTotalCredits());
        record.setUser(userRepository.findByUsername(recordRequestDto.getUserCode())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_USERNAME, new String[]{recordRequestDto.getUserCode()}))
        );
        if(record.getGPA() == 0.0)
            record.setRating(RateConstant.UNKNOWN);
        else if(record.getGPA()<2.0)
            record.setRating(RateConstant.WEAK);
        else if(record.getGPA()<2.5)
            record.setRating(RateConstant.MEDIUM);
        else if(record.getGPA()<3.2)
            record.setRating(RateConstant.GOOD);
        else record.setRating(RateConstant.EXCElLENT);
        return recordMapper.toRecordResponseDto(recordRepository.save(record));
    }

    @Override
    public RecordResponseDto updateRecord( RecordRequestDto recordRequestDto) {
        Record record = recordRepository.findAllByUserUsername(recordRequestDto.getUserCode());
        if(recordRequestDto.getTotalScore()>0) record.setTotalScore(recordRequestDto.getTotalScore());
        if(recordRequestDto.getTotalCredits()>0) record.setTotalCredits(recordRequestDto.getTotalCredits());
        record.setGPA((double) record.getTotalScore() /record.getTotalCredits());
        if(record.getGPA() == 0.0)
            record.setRating(RateConstant.UNKNOWN);
        else if(record.getGPA()<2.0)
            record.setRating(RateConstant.WEAK);
        else if(record.getGPA()<2.5)
            record.setRating(RateConstant.MEDIUM);
        else if(record.getGPA()<3.2)
            record.setRating(RateConstant.GOOD);
        else record.setRating(RateConstant.EXCElLENT);
        return recordMapper.toRecordResponseDto(recordRepository.save(record));
    }

    @Override
    public List<RecordResponseDto> getAllRecords() {
        return recordMapper.toRecordResponseDtoList(recordRepository.findAll());
    }

    @Override
    public RecordResponseDto getRecordsByUserCode(String userCode) {
        return recordMapper.toRecordResponseDto(recordRepository.findAllByUserUsername(userCode));
    }

    @Override
    public CommonResponseDto deleteRecord(String id) {
        Record record = recordRepository.getById(id);
        recordRepository.delete(record);
        return new CommonResponseDto(true, CommonConstant.SUCCESS);
    }

    @Override
    public long countStudentsByRatingAndClassName(String rating, String className) {
        return recordRepository.countStudentsByRatingAndClassName(rating, className);
    }

    @Override
    public long countStudentsByRatingAndDepartmentName(String rating, String departmentName) {
        return recordRepository.countStudentsByRatingAndDepartmentName(rating, departmentName);
    }

    @Override
    public List<RecordResponseDto> findAllByRatingAndClassName(String rating, String className) {
        return recordMapper.toRecordResponseDtoList(recordRepository.findAllByRatingAndClassName(rating,className));
    }

    @Override
    public List<RecordResponseDto> findAllByRatingAndDepartmentName(String rating, String departmentName) {
        return recordMapper.toRecordResponseDtoList(recordRepository.findAllByRatingAndDepartmentName(rating,departmentName));
    }

    @Override
    public long countStudentsByRating(String rating) {
        return recordRepository.countStudentsByRating(rating);
    }
}
