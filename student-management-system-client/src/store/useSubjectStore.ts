import { create } from 'zustand';

import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Subject } from '@/types/subject.type';

interface SubjectStore {
  subjects: Subject[];
  subject: Subject | null;
  loading: boolean;
  error: string | null;
  fetchSubjects: () => Promise<void>;
  createSubject: (Subject: Omit<Subject, 'id'>) => Promise<void>;
  updateSubject: (id: string, Subject: Omit<Subject, 'id'>) => Promise<void>;
  deleteSubject: (id: string) => Promise<void>;
}

export const useSubjectStore = create<SubjectStore>((set) => ({
  subjects: [],
  subject: null,
  loading: false,
  error: null,

  fetchSubjects: async () => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.get<{ items: Subject[] }>(ApiConstant.subjects.getAll);
      const subjects = data.items;
      set({ subjects });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },

  createSubject: async (Subject) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.post<Subject>(ApiConstant.subjects.create, Subject);
      set((state) => ({ subjects: [...state.subjects, data] }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  updateSubject: async (id, Subject) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.put<Subject>(ApiConstant.subjects.update.replace(':id', id), Subject);
      set((state) => ({
        subjects: state.subjects.map((s) => (s.id === id ? data : s)),
      }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  deleteSubject: async (id) => {
    set({ loading: true, error: null });
    try {
      await api.delete(ApiConstant.subjects.delete.replace(':id', id));
      set((state) => ({
        subjects: state.subjects.filter((s) => s.id !== id),
      }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },
}));
