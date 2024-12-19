import { Classroom } from '@/types/classroom.type';
import { Document } from '@/types/document.type';

export interface Subject {
  id: string;
  name: string;
  code: string;
  numberOfCredits: number; // Số tín chỉ
  regularCoefficient: number; // Điểm thường xuyên
  midTermCoefficient: number; // Điểm giữa kỳ
  finalCoefficient: number; // Điểm cuối cùng
  description?: string; // Mô tả
  prerequisiteSubjects?: string; // Điều kiện tiên quyết
  createdAt?: string;
  classrooms?: Classroom[];
  documents?: Document[];
}
