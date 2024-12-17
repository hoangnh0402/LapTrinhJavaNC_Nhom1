package com.example.projectbase.constant;

public class UrlConstant {

  public static class Auth {
    private static final String PRE_FIX = "/auth";
    public static final String FORGET_PASSWORD = PRE_FIX + "/forget-password";
    public static final String CHANGE_PASSWORD = PRE_FIX + "/change-password";
    public static final String LOGIN = PRE_FIX + "/login";
    public static final String LOGOUT = PRE_FIX + "/logout";

    private Auth() {
    }
  }

  public static class User {
    private static final String PRE_FIX = "/user";

    public static final String CREATE_STUDENT = PRE_FIX+"/student";
    public static final String CREATE_TEACHER = PRE_FIX+"/teacher";
    public static final String GET_STUDENT = PRE_FIX+"/student";
    public static final String GET_TEACHER = PRE_FIX+"/teacher";
    public static final String UPDATE_USER = PRE_FIX;
    public static final String LOCK_USER = PRE_FIX+"/lock";
    public static final String UNLOCK_USER = PRE_FIX+"/unlock";
    public static final String GET_USER = PRE_FIX + "/id/{userId}";
    public static final String GET_USER_BY_USERCODE = PRE_FIX + "/{userCode}";
    public static final String GET_CURRENT_USER = PRE_FIX + "/current";

    private User() {
    }
  }

  public static class Department{
    private static final String PRE_FIX = "/department";

    public static final String GET_DEPARTMENTS = PRE_FIX;
    public static final String GET_DEPARTMENT = PRE_FIX + "/id";
    public static final String CREATE_DEPARTMENT = PRE_FIX;
    public static final String UPDATE_DEPARTMENT = PRE_FIX;
    public static final String DELETE_DEPARTMENT = PRE_FIX;
    private Department() {

    }
  }

  public static class Class{
    private static final String PRE_FIX = "/class";

    public static final String GET_CLASSES = PRE_FIX;
    public static final String GET_CLASS = PRE_FIX + "/id";
    public static final String CREATE_CLASS = PRE_FIX;
    public static final String UPDATE_CLASS = PRE_FIX;
    public static final String DELETE_CLASS = PRE_FIX;
    private Class() {

    }
  }

  public static class Record{
    public static final String PRE_FIX = "/record";
    public static final String CREATE_RECORD = PRE_FIX;
    public static final String UPDATE_RECORD = PRE_FIX;
    public static final String GET_ALL_RECORD = PRE_FIX;
    public static final String GET_RECORD_BY_USERCODE = PRE_FIX+"/user";
    public static final String DELETE_RECORD = PRE_FIX;
    public static final String COUNT_BY_RATING = PRE_FIX +"/count";
    public static final String COUNT_BY_RATING_AND_CLASS = PRE_FIX +"/count/class";
    public static final String COUNT_BY_RATING_AND_DEPARTMENT = PRE_FIX +"/count/department";
    public static final String GET_BY_RATING_AND_CLASS = PRE_FIX +"/rate/class";
    public static final String GET_BY_RATING_AND_DEPARTMENT = PRE_FIX +"/rate/department";
    private Record() {

    }
  }

  public static class Document {
    public static final String GET_ALL_DOCUMENTS = "/documents";
    public static final String UPLOAD_DOCUMENT = "/documents/upload";
    public static final String DOWNLOAD_DOCUMENT = "/documents/download";
    public static final String GET_DOCUMENT = "/documents/{id}";
    public static final String GET_DOCUMENTS_BY_SUBJECT = "/documents/subject";
  }
  public static final class Classroom {
    public static final String BASE = "/admin/classroom";
    public static final String CREATE_CLASSROOM = BASE; // POST /admin/classroom
    public static final String GET_ALL_CLASSROOMS = "/user/classroom"; // GET /user/classroom
    public static final String GET_CLASSROOM_BY_ID = "/classroom/id"; // GET /classroom/id
    public static final String UPDATE_CLASSROOM = BASE; // PUT /admin/classroom
    public static final String DELETE_CLASSROOM = BASE; // DELETE /admin/classroom
    public static final String GET_CLASSROOMS_BY_START_DATE = "/user/classroom/start-date"; // GET /user/classroom/start-date
    public static final String GET_CLASSROOMS_BY_SCHEDULE = "/user/classroom/schedule"; // GET /user/classroom/schedule
    public static final String GET_CLASSROOMS_BY_SUBJECT_CODE = "/user/classroom/subject/code"; // GET /user/classroom/subject/code
    public static final String ADD_STUDENT_TO_CLASSROOM = BASE + "/{classroomId}/add-student"; // POST /admin/classroom/{classroomId}/add-student
    public static final String REMOVE_STUDENT_FROM_CLASSROOM = BASE + "/{classroomId}/{userId}"; // DELETE /admin/classroom/{classroomId}/{userId}
  }

  public static final class Subject {
    public static final String BASE = "/admin/subject";
    public static final String CREATE_SUBJECT = BASE; // POST /admin/subject
    public static final String GET_ALL_SUBJECTS = "/subject"; // GET /subject
    public static final String GET_SUBJECTS = BASE; // GET /admin/subject
    public static final String FIND_SUBJECTS = "/user/subject/find"; // GET /user/subject/find
    public static final String UPDATE_SUBJECT = BASE; // PUT /admin/subject
    public static final String DELETE_SUBJECT = BASE; // DELETE /admin/subject
    public static final String GET_SUBJECT_BY_ID = "/subject/id"; // GET /subject/id
  }

  public static class Enrollment {
    private static final String PRE_FIX = "/enroll";
    public static final String REGISTER_SUBJECT = PRE_FIX + "/register";
    public static final String UPDATE_ENROLLMENT = PRE_FIX;
  }


}
