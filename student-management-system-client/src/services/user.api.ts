import { ApiConstant } from '@/constants/api.constant';
import { api } from './api.service';
import { User } from '@/types/user.type';

const userApi = () => ({
  getByUsercode: async (id: string): Promise<User> => {
    const response = await api.get(ApiConstant.students.getByUsercode.replace(':userCode', id));
    return response.data; // Chỉ trả về `data` từ AxiosResponse
  },
});

export const { getByUsercode } = userApi();
