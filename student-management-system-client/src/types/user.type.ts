import { Class } from '@/types/class.type';
import { Department } from '@/types/department.type';
import { Enrollment } from '@/types/enrollment.type';

export interface User {
  id: string;
  classId?: string | null;
  username: string;
  password?: string;
  email?: string;
  phoneNumber?: string;
  fullName: string;
  gender: string;
  avatar?: string | null;
  birthday?: string | null;
  address?: string | null;
  role?: string;
  userCode?: string;
  roleName?: Role;
  createdDate?: string;
  lastModifiedDate?: string;

  enrollments?: Enrollment[];
  department?: Department;
  class?: Class;
}

export enum Role {
  STUDENT = 'ROLE_STUDENT',
  TEACHER = 'ROLE_TEACHER',
  ADMIN = 'ROLE_ADMIN',
}

export enum Gender {
  MALE = 'Nam',
  FEMALE = 'Nữ',
}
