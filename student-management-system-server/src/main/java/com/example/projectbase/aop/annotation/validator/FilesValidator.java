package com.example.projectbase.aop.annotation.validator;

import com.example.projectbase.aop.annotation.ValidFiles;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class FilesValidator implements ConstraintValidator<ValidFiles, List<MultipartFile>> {

  @Override
  public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext constraintValidatorContext) {
    boolean result = true;
    if(CollectionUtils.isNotEmpty(files)) {
      for(MultipartFile file : files) {
        String contentType = file.getContentType();
        if (!isSupportedContentType(Objects.requireNonNull(contentType))) {
          result = false;
          break;
        }
      }
    }
    return result;
  }

  private boolean isSupportedContentType(String contentType) {
    return contentType.equals("image/png")
        || contentType.equals("image/jpg")
        || contentType.equals("image/jpeg")
        || contentType.equals("image/webp")
        || contentType.equals("image/gif")
        || contentType.equals("video/mp4")
        || contentType.equals("application/pdf")
        || contentType.equals("application/msword")
        || contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
        || contentType.equals("application/vnd.ms-excel")
        || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        || contentType.equals("application/vnd.ms-powerpoint")
        || contentType.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation");
  }
}
