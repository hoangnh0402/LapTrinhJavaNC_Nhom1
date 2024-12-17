package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.request.UploadDocumentDTO;
import com.example.projectbase.domain.entity.Document;
import com.example.projectbase.domain.entity.Subject;
import com.example.projectbase.repository.DocumentRepository;
import com.example.projectbase.repository.SubjectRepository;
import com.example.projectbase.service.DocumentService;
import com.example.projectbase.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.example.projectbase.constant.ErrorMessage.Document.ERR_READ_FILE;
import static com.example.projectbase.constant.ErrorMessage.Document.ERR_SAVE_FILE;
import static com.example.projectbase.constant.ErrorMessage.User.ERR_NOT_FOUND_ID;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final SubjectRepository subjectRepository;
    private final UploadFileUtil uploadFileUtil;

    private final Path storagePath = Paths.get("documents");

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public List<Document> uploadDocument(UploadDocumentDTO uploadDocumentDTO) {
        try {
            List<Document> savedDocuments = new ArrayList<>();

            // Lấy thông tin môn học theo ID
            Subject subject = subjectRepository.findSubjectById(uploadDocumentDTO.getSubjectId());
            if (subject == null) {
                throw new RuntimeException(String.format(ERR_NOT_FOUND_ID, uploadDocumentDTO.getSubjectId()));
            }

            // Duyệt qua danh sách tệp tải lên
            for (MultipartFile file : uploadDocumentDTO.getFiles()) {
                // Kiểm tra tệp có hợp lệ hay không
                if (file.isEmpty()) {
                    throw new RuntimeException("File cannot be empty");
                }

                // Xác định loại tệp
                String contentType = file.getContentType();
                if (contentType == null) {
                    throw new RuntimeException("Invalid file: Content type is null");
                }

                // Chỉ cho phép các loại tệp được hỗ trợ
                if (!contentType.startsWith("image/") &&
                        !contentType.startsWith("video/") &&
                        !contentType.equals("application/pdf")) {
                    throw new RuntimeException("Unsupported file type: " + contentType);
                }

                // Gọi util upload file lên Cloudinary và lấy URL
                String fileUrl = uploadFileUtil.uploadFile(file);

                // Tạo đối tượng Document
                Document document = Document.builder()
                        .name(uploadDocumentDTO.getName())
                        .type(uploadDocumentDTO.getType())
                        .description(uploadDocumentDTO.getDescription())
                        .path(fileUrl) // Đường dẫn là URL sau khi upload lên Cloudinary
                        .subject(subject)
                        .build();

                // Lưu vào cơ sở dữ liệu
                savedDocuments.add(documentRepository.save(document));
            }

            return savedDocuments;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading document: " + e.getMessage(), e);
        }
    }


    @Override
    public byte[] downloadDocument(String id) {
        // Lấy tài liệu theo ID
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(ERR_NOT_FOUND_ID, id)));

        try {
            String documentPath = document.getPath();
            if (documentPath.startsWith("http://") || documentPath.startsWith("https://")) {
                // Xử lý URL (ví dụ: tải file từ Cloudinary)
                URL url = new URL(documentPath);
                try (InputStream inputStream = url.openStream()) {
                    return inputStream.readAllBytes();
                }
            } else {
                // Xử lý đường dẫn cục bộ
                Path filePath = Paths.get(documentPath);
                return Files.readAllBytes(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(ERR_READ_FILE, e);
        }
    }

    @Override
    public Document getDocumentById(String id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format(ERR_NOT_FOUND_ID, id)));
    }

    @Override
    public List<Document> getAllDocumentsBySubject(String id) {
        Subject subject = subjectRepository.findSubjectById(id);
        if (subject == null) {
            throw new RuntimeException(String.format(ERR_NOT_FOUND_ID, id));
        }
        return documentRepository.findAllBySubject_Id(id);
    }
}
