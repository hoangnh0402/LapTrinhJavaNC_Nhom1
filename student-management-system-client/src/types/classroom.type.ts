import { User } from '@/types/user.type';
import { Enrollment } from '@/types/enrollment.type';

export interface Classroom {
  id: string;
  code: string;
  subjectId: string;
  numberOfStudents: number;
  teacherId: string;
  schedule: string;
  room: string;
  startDate?: string;
  createdAt?: string;

  teacher?: User;
  enrollments?: Enrollment[];
}
