/* eslint-disable @typescript-eslint/no-explicit-any */
import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Enrollment } from '@/types/enrollment.type';

const enrollmentApi = () => ({
  fetchById: async (id: string): Promise<Enrollment[]> => {
    const response = await api.get<Enrollment[]>(ApiConstant.enrollment.getAll.replace(':id', id));
    return response.data;
  },
  updateScore: async (data: any): Promise<any> => {
    const response = await api.put(ApiConstant.enrollment.updateScore, data);
    return response.data;
  },
});

export const { fetchById, updateScore } = enrollmentApi();
