package com.example.projectbase.domain.dto.request;

import com.example.projectbase.aop.annotation.ValidFiles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO for uploading documents")
public class UploadDocumentDTO {

    @Schema(description = "Name of the document", required = true)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Type of the document", required = true)
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @Schema(description = "Description of the document", required = true)
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Schema(description = "Subject ID related to the document", required = true)
    @NotBlank(message = "Subject ID cannot be blank")
    private String subjectId;

    @Schema(description = "List of files to be uploaded")
    @ValidFiles
    private List<MultipartFile> files = new ArrayList<>();
}

