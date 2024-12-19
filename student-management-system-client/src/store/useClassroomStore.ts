import { create } from 'zustand';

import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Classroom } from '@/types/classroom.type';

interface ClassroomStore {
  classrooms: Classroom[];
  classroom: Classroom | null;

  loading: boolean;
  error: string | null;
  fetchClassrooms: () => Promise<void>;
  createClassroom: (Classroom: Omit<Classroom, 'id'>) => Promise<void>;
  updateClassroom: (id: string, Classroom: Omit<Classroom, 'id'>) => Promise<void>;
  deleteClassroom: (id: string) => Promise<void>;
  getClassroomById: (id: string) => Promise<void>;
}

export const useClassroomStore = create<ClassroomStore>((set) => ({
  classrooms: [],
  classroom: null,
  loading: false,
  error: null,

  fetchClassrooms: async () => {
    set({ loading: true, error: null });
    try {
      const { data: classrooms } = await api.get<Classroom[]>(ApiConstant.classrooms.getAll);
      set({ classrooms });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },

  createClassroom: async (Classroom) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.post<Classroom>(ApiConstant.classrooms.create, Classroom);
      set((state) => ({ classrooms: [...state.classrooms, data] }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  updateClassroom: async (id, Classroom) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.put<Classroom>(ApiConstant.classrooms.update.replace(':id', id), Classroom);
      set((state) => ({
        classrooms: state.classrooms.map((c) => (c.id === id ? data : c)),
      }));
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },

  deleteClassroom: async (id) => {
    set({ loading: true, error: null });
    try {
      await api.delete(ApiConstant.classrooms.delete.replace(':id', id));
      set((state) => ({
        classrooms: state.classrooms.filter((c) => c.id !== id),
      }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  getClassroomById: async (id) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.get<Classroom>(ApiConstant.classrooms.getById.replace(':id', id));
      set({ classroom: data });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },
}));
