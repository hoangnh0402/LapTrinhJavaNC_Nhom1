package com.example.projectbase.aop.annotation.validator;


import com.example.projectbase.validator.annotation.ValidFileImage;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FileImageValidator implements ConstraintValidator<ValidFileImage, MultipartFile> {

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
    boolean result = true;
    if(file != null) {
      String contentType = file.getContentType();
      if (!isSupportedContentType(Objects.requireNonNull(contentType))) {
        result = false;
      }
    }
    return result;
  }

  private boolean isSupportedContentType(String contentType) {
    return contentType.equals("image/png")
        || contentType.equals("image/jpg")
        || contentType.equals("image/jpeg")
        || contentType.equals("image/webp")
        || contentType.equals("image/gif");
  }

}
