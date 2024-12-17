import { Classroom } from '@/types/classroom.type';
import { Document } from '@/types/document.type';

export interface AvailableRegisterSubject {
  id: string;
  name: string;
  code: string;
  numberOfCredits: number;
  regularCoefficient: number;
  midTermCoefficient: number;
  finalCoefficient: number;
  description?: string;
  prerequisiteSubjects?: string;
  createdAt?: string;
  documents?: Document[];

  //Add schedule + classroomId for registering
  schedule?: string;
  classroomId?: string;
}
