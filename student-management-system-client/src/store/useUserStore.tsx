import { create } from 'zustand';
import { persist } from 'zustand/middleware';

import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { User } from '@/types/user.type';

interface useUserStore {
  user: User | null;
  loading: boolean;
  isAuthenticated: boolean;
  checkingAuth: boolean;
  error: string | null;
  login: (studentId: string, password: string) => Promise<void>;
  logout: () => Promise<boolean>;
  checkAuth: () => Promise<void>;
}

export const useUserStore = create<useUserStore>()(
  persist(
    (set) => ({
      user: null,
      loading: false,
      isAuthenticated: false,
      checkingAuth: false,
      error: null,

      login: async (username: string, password: string) => {
        set({ loading: true, error: null });
        try {
          const { data } = await api.post(ApiConstant.auth.login, { emailOrPhone: username, password });
          localStorage.setItem('token', data.accessToken);
          const { data: user } = await api.get(ApiConstant.auth.profile);
          set({ user, isAuthenticated: true });
        } catch (error) {
          set({ error: error.response.data.message });
          throw new Error(error.response.data.message);
        } finally {
          set({ loading: false });
        }
      },

      logout: async () => {
        set({ loading: true, error: null });
        try {
          await api.post(ApiConstant.auth.logout);
          localStorage.removeItem('token');
          set({ user: null, isAuthenticated: false });
          return true;
        } catch (error) {
          set({ loading: false, error: error.response.data.message });
          return false;
        } finally {
          set({ loading: false });
        }
      },

      checkAuth: async () => {
        set({ checkingAuth: true });
        try {
          const response = await api.get(ApiConstant.auth.profile);
          set({ user: response.data, checkingAuth: false });
        } catch (error) {
          set({ loading: false, error: error.message });
        } finally {
          set({ loading: false });
        }
      },
    }),
    {
      name: 'auth-storage',
      partialize: (state) => ({
        isAuthenticated: state.isAuthenticated,
        user: state.user,
      }),
    },
  ),
);
