import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { User } from '@/types/user.type';

const studentApi = () => ({
  fetchStudents: async (): Promise<User[]> => {
    const { data } = await api.get<User[]>(ApiConstant.students.getAll);
    return data;
  },
});
export const { fetchStudents } = studentApi();
