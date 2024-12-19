package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.request.UploadDocumentDTO;
import com.example.projectbase.domain.entity.Document;
import com.example.projectbase.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestApiV1
public class DocumentController {
    private final DocumentService documentService;

    @Tags({@Tag(name = "document-controller-admin")})
    @Operation(summary = "API get all documents")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(UrlConstant.Document.GET_ALL_DOCUMENTS)
    public ResponseEntity<?> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return VsResponseUtil.success(documents);
    }

    @Tags({@Tag(name = "document-controller-admin")})
    @Operation(summary = "API upload document")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocument(@Valid @ModelAttribute UploadDocumentDTO uploadDocumentDTO) {
        try {
            List<Document> documents = documentService.uploadDocument(uploadDocumentDTO);
            return ResponseEntity.ok(documents);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading document: " + e.getMessage());
        }
    }

    @Tags({@Tag(name = "document-controller-admin")})
    @Operation(summary = "API download document")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping(UrlConstant.Document.DOWNLOAD_DOCUMENT)
    public ResponseEntity<?> downloadDocument(@RequestParam("id") String id) {
        byte[] fileContent = documentService.downloadDocument(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=document_" + id + ".pdf")
                .body(fileContent);
    }

    @Tags({@Tag(name = "document-controller-admin")})
    @Operation(summary = "API get document by id")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping(UrlConstant.Document.GET_DOCUMENT)
    public ResponseEntity<?> getDocumentById(@RequestParam("id") String id) {
        Document document = documentService.getDocumentById(id);
        return VsResponseUtil.success(document);
    }

    @Tags({@Tag(name = "document-controller-admin")})
    @Operation(summary = "API get all documents by subject")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'STUDENT')")
    @GetMapping(UrlConstant.Document.GET_DOCUMENTS_BY_SUBJECT)
    public ResponseEntity<?> getAllDocumentsBySubject(@RequestParam("subjectId") String subjectId) {
        List<Document> documents = documentService.getAllDocumentsBySubject(subjectId);
        return VsResponseUtil.success(documents);
    }
}

