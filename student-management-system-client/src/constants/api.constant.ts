export const ApiConstant = {
  auth: {
    login: '/auth/login',
    logout: '/auth/logout',
    profile: '/user/current',
  },
  students: {
    getAll: '/user/student',
    getById: '/user/:id',
    getByUsercode: '/user/:userCode',
    create: '/user/student',
    update: '/user?userId=:id',
    delete: '/user/lock?userId=:id',
  },
  teachers: {
    getAll: '/user/teacher',
    getById: '/user/:id',
    getByUsercode: '/user/teacher/:userCode',
    create: '/user/teacher',
    update: '/user?userId=:id',
    delete: '/user/lock?userId=:id',
  },
  subjects: {
    getAll: '/subject?pageSize=1000',
    getById: '/subject/id?subjectId=:id',
    create: '/admin/subject',
    update: '/admin/subject?subjectId=:id',
    delete: '/admin/subject?subjectId=:id',
  },
  classes: {
    getAll: '/class',
    create: '/class',
    update: '/class',
    delete: '/class',
    getById: '/class/:id',
  },
  classrooms: {
    getAll: '/user/classroom',
    getById: '/classroom/id?classroomId=:id',
    create: '/admin/classroom',
    update: '/admin/classroom?classroomId=:id',
    delete: '/admin/classroom?classroomId=:id',
    getStudents: '/:id/students1',
    addStudent: '/admin/classroom/:id/add-student',
    removeStudent: '/admin/classroom/:classroomId/:studentId',
    getClassByStudent: 'api/v1/classrooms/byStudentCode/:username',
  },
  documents: {
    getAll: '/documents',
    getAllBySubject: '/documents/subject',
    getById: '/documents/{id}',
    dowload: '/documents/download',
    upload: '/upload',
  },
  enrollment: {
    register: 'enroll/register',
    getAll: '/:id/students',
    updateScore: '/enroll',
  },
  records: {
    getTotal: 'record/count/department',
  },
};

export const ApiConstantUser = {
  classrooms: {
    getAll: '/api/v1/classrooms/byStudentCode/:studentCode',
  },

  subjects: {
    getAll: '/subject',
  },

  enrollment: {
    reject: 'reject/:classroomId',
  },
};
