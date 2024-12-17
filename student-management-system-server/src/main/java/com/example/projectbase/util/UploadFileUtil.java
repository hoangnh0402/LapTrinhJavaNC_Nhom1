package com.example.projectbase.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.projectbase.exception.UploadFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class UploadFileUtil {

  private final Cloudinary cloudinary;

  private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList(
          "application/pdf", "image/jpeg", "image/png", "video/mp4"
  );

  private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

  public String uploadFile(MultipartFile file) {
    validateFile(file);

    try {
      String resourceType = getResourceType(file);
      Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", resourceType));
      return result.get("secure_url").toString();
    } catch (IOException e) {
      log.error("Error uploading file to Cloudinary", e);
      throw new UploadFileException("Failed to upload file. Please check the file format and size.");
    }
  }

  public String uploadImage(byte[] bytes) {
    try {
      Map<?, ?> result = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "image"));
      return result.get("secure_url").toString();
    } catch (IOException e) {
      log.error("Error uploading image to Cloudinary", e);
      throw new UploadFileException("Upload image failed!");
    }
  }

  public void destroyFileWithUrl(String url) {
    int startIndex = url.lastIndexOf("/") + 1;
    int endIndex = url.lastIndexOf(".");
    String publicId = url.substring(startIndex, endIndex);

    try {
      Map<?, ?> result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
      log.info(String.format("Destroy file public id %s: %s", publicId, result.toString()));
    } catch (IOException e) {
      log.error("Error deleting file from Cloudinary", e);
      throw new UploadFileException("Remove file failed!");
    }
  }

  private void validateFile(MultipartFile file) {
    if (file.isEmpty()) {
      throw new UploadFileException("File cannot be empty");
    }

    if (file.getSize() > MAX_FILE_SIZE) {
      throw new UploadFileException("File size exceeds the maximum limit of 10 MB");
    }

    String contentType = file.getContentType();
    if (contentType == null || !SUPPORTED_MIME_TYPES.contains(contentType)) {
      throw new UploadFileException("Unsupported file type: " + contentType);
    }
  }

  private static String getResourceType(MultipartFile file) {
    String contentType = file.getContentType();
    if (contentType != null) {
      if (contentType.startsWith("image/")) {
        return "image";
      } else if (contentType.startsWith("video/")) {
        return "video";
      } else if (contentType.equals("application/pdf")) { // Hỗ trợ PDF
        return "raw";
      } else {
        throw new UploadFileException("Unsupported file type: " + contentType);
      }
    } else {
      throw new UploadFileException("Invalid file: Content type is null!");
    }
  }
}
