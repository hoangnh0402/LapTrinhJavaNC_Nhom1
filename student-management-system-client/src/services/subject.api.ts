import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Subject } from '@/types/subject.type';

const subjectApi = () => ({
  getById: async (id: string): Promise<Subject> => {
    const { data } = await api.get<Subject>(ApiConstant.subjects.getById.replace(':id', id));
    return data;
  },
  fetchSubjects: async (): Promise<Subject[]> => {
    const { data } = await api.get<{ items: Subject[] }>(ApiConstant.subjects.getAll);
    return data.items;
  },
});

export const { getById, fetchSubjects } = subjectApi();
