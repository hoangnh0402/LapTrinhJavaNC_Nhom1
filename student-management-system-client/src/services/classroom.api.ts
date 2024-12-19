import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Classroom } from '@/types/classroom.type';

const classroomApi = () => ({
  getClassByStudent: async (username: string): Promise<Classroom[]> => {
    const { data } = await api.get<Classroom[]>(
      ApiConstant.classrooms.getClassByStudent.replace(':username', username),
    );
    return data;
  },
  fetchClassrooms: async (): Promise<Classroom[]> => {
    const { data } = await api.get<Classroom[]>(ApiConstant.classrooms.getAll);
    return data;
  },
  getClassroomById: async (id: string): Promise<Classroom> => {
    const { data } = await api.get<Classroom>(ApiConstant.classrooms.getById.replace(':id', id));
    return data;
  },
});

export const { getClassByStudent, fetchClassrooms, getClassroomById } = classroomApi();
