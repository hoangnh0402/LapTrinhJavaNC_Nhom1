import { create } from 'zustand';

import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { User } from '@/types/user.type';

interface TeacherStore {
  teachers: User[];
  loading: boolean;
  error: string | null;
  fetchTeachers: () => Promise<void>;
  createTeacher: (teacher: Omit<User, 'id'>) => Promise<void>;
  updateTeacher: (id: string, teacher: Omit<User, 'id'>) => Promise<void>;
  deleteTeacher: (id: string) => Promise<void>;
}

export const useTeacherStore = create<TeacherStore>((set) => ({
  teachers: [],
  loading: false,
  error: null,

  fetchTeachers: async () => {
    set({ loading: true, error: null });
    try {
      const { data: teachers } = await api.get<User[]>(ApiConstant.teachers.getAll);
      set({ teachers });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },

  createTeacher: async (teacher) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.post<User>(ApiConstant.teachers.create, teacher);
      set((state) => ({ teachers: [...state.teachers, data] }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  updateTeacher: async (id, teacher) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.put<User>(ApiConstant.teachers.update.replace(':id', id), teacher);
      set((state) => ({
        teachers: state.teachers.map((t) => (t.id === id ? data : t)),
      }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  deleteTeacher: async (id) => {
    set({ loading: true, error: null });
    try {
      await api.patch(ApiConstant.teachers.delete.replace(':id', id));
      set((state) => ({
        teachers: state.teachers.filter((t) => t.id !== id),
      }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },
}));
