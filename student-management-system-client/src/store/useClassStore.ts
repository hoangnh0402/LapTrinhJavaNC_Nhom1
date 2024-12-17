import { create } from 'zustand';

import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Class } from '@/types/class.type';

interface ClassStore {
  classes: Class[];
  loading: boolean;
  error: string | null;
  fetchClasses: () => Promise<Class[]>;
}

export const useClassStore = create<ClassStore>((set) => ({
  classes: [],
  loading: false,
  error: null,

  fetchClasses: async () => {
    set({ loading: true, error: null });
    try {
      const { data: classes } = await api.get<Class[]>(ApiConstant.classes.getAll);
      set({ classes });
      return classes;
    } catch (error) {
      set({ error: error.message });
      return [];
    } finally {
      set({ loading: false });
    }
  },
}));
