import { User } from '@/types/user.type';

export interface Class {
  id: string;
  departmentId: string;
  name: string;
  code: string;
  academicYear: string;
  createdAt?: string;

  users?: User[];
}
