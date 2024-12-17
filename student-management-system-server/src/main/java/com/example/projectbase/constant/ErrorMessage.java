package com.example.projectbase.constant;

public class ErrorMessage {

  public static final String ERR_EXCEPTION_GENERAL = "error.general";
  public static final String UNAUTHORIZED = "error.unauthorized";
  public static final String FORBIDDEN = "error.forbidden";
  public static final String FORBIDDEN_UPDATE_DELETE = "error.forbidden.update_delete";

  // lỗi xác thực dữ liệu
  public static final String INVALID_SOME_THING_FIELD = "error.validation.general";
  public static final String INVALID_FORMAT_SOME_THING_FIELD = "error.validation.format";
  public static final String INVALID_SOME_THING_FIELD_IS_REQUIRED = "error.validation.required";
  public static final String NOT_BLANK_FIELD = "error.validation.not_blank";
  public static final String INVALID_FORMAT_PASSWORD = "error.validation.password_format";
  public static final String INVALID_DATE = "error.validation.date_format";
  public static final String INVALID_DATE_FEATURE = "error.validation.future_date";
  public static final String INVALID_DATETIME = "error.validation.datetime_format";

  public static class Auth {
    public static final String ERR_INCORRECT_EMAIL = "error.auth.incorrect_email";
    public static final String INVALID_REPEAT_PASSWORD = "error.auth.invalid_repeat_password";
    public static final String ERR_INCORRECT_USERNAME = "error.auth.incorrect_username";
    public static final String ERR_INCORRECT_PASSWORD = "error.auth.incorrect_password";
    public static final String ERR_ACCOUNT_NOT_ENABLED = "error.auth.account_not_enabled";
    public static final String ERR_ACCOUNT_LOCKED = "error.auth.account_locked";
    public static final String INVALID_REFRESH_TOKEN = "error.auth.invalid_refresh_token";
    public static final String EXPIRED_REFRESH_TOKEN = "error.auth.expired_refresh_token";
  }

  public static class User {
    public static final String ERR_NOT_FOUND_USERNAME = "error.user.not_found_username";
    public static final String ERR_NOT_FOUND_ID = "error.user.not_found_id";
  }

  public static class Subject {
    public static final String ERR_NOT_FOUND_ID = "error.subject.not_found_id";
    public static final String ERR_NOT_FOUND_NAME = "error.subject.not_found_name";
    public static final String ERR_INVALID_SUBJECT = "error.subject.invalid";
  }

  public static class Classroom {
    public static final String ERR_NOT_FOUND_ID = "error.classroom.not_found_id";
    public static final String ERR_INVALID_DATA = "error.classroom.invalid_data";
    public static final String ERR_CANNOT_DELETE = "error.classroom.cannot_delete";
  }

  public static class Department {
    public static final String ERR_NOT_FOUND_ID = "error.department.not_found_id";
  }

  public static class Class {
    public static final String ERR_NOT_FOUND_ID = "error.class.not_found_id";
  }

  public static class Document {
    public static final String ERR_NOT_FOUND_ID = "error.document.not_found_id";
    public static final String ERR_SAVE_FILE = "error.document.save_file";
    public static final String ERR_READ_FILE = "error.document.read_file";
    public static final String ERR_INVALID_PATH = "error.document.invalid_path";
  }

  public static class Enrollment {
    public static final String USER_ALREADY_IN_CLASSROOM = "error.enrollment.user_already_in_classroom";
    public static final String ERR_NOT_FOUND_ENROLLMENT = "error.enrollment.not_found";
    public static final String USER_NOT_STUDENT = "error.enrollment.user_not_student";
  }
}
