package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.MessageConstrant;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.request.RequestCreateSubjectDTO;
import com.example.projectbase.domain.dto.request.UpdateRequestSubjectDTO;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.SubjectResponseDTO;
import com.example.projectbase.domain.entity.Subject;
import com.example.projectbase.domain.mapper.SubjectMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.SubjectRepository;
import com.example.projectbase.service.SubjectService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public SubjectResponseDTO createSubject(RequestCreateSubjectDTO subjectRequestDTO) {
        Subject subject = subjectMapper.toEntity(subjectRequestDTO);
        subject = subjectRepository.save(subject);
        return subjectMapper.toDto(subject);
    }

    @Override
    public PaginationResponseDto<Subject> readAllSubjects(PaginationFullRequestDto paginationRequestDTO) {
        Pageable pageable = PaginationUtil.buildPageable(paginationRequestDTO);
        Page<Subject> subjectPage = subjectRepository.findAll(pageable);

        PagingMeta pagingMeta = new PagingMeta(
                subjectPage.getTotalElements(),
                subjectPage.getTotalPages(),
                subjectPage.getNumber(),
                subjectPage.getSize(),
                paginationRequestDTO.getSortBy(),
                paginationRequestDTO.getIsAscending().toString()
        );

        return new PaginationResponseDto<>(pagingMeta, subjectPage.getContent());
    }

    @Override
    public PaginationResponseDto<Subject> readSubjects(PaginationFullRequestDto paginationRequestDTO) {
        return readAllSubjects(paginationRequestDTO); // Tái sử dụng logic readAllSubjects
    }

    @Override
    public List<SubjectResponseDTO> findSubjects(String name) {
        List<Subject> subjects = subjectRepository.findAllByNameContainingIgnoreCase(name);
        if (subjects.isEmpty()) {
            throw new NotFoundException(
                    ErrorMessage.Subject.ERR_NOT_FOUND_NAME,
                    new String[]{name}
            );
        }
        return subjects.stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public SubjectResponseDTO updateSubject(String subjectId, UpdateRequestSubjectDTO subjectRequestDTO) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorMessage.Subject.ERR_NOT_FOUND_ID,
                        new String[]{subjectId}
                ));

        subjectMapper.updateEntityFromDto(subjectRequestDTO, subject);
        subject = subjectRepository.save(subject);
        return subjectMapper.toDto(subject);
    }

    @Override
    public CommonResponseDto deleteSubject(String subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NotFoundException(
                        ErrorMessage.Subject.ERR_NOT_FOUND_ID,
                        new String[]{subjectId}
                ));

        subjectRepository.deleteById(subjectId);
        return new CommonResponseDto(true, MessageConstrant.SUCCESS);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

}