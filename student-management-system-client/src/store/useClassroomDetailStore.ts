import { create } from 'zustand';

import { ApiConstant } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Subject } from '@/types/subject.type';
import { User } from '@/types/user.type';
import { Classroom } from '@/types/classroom.type';
import { useClassStore } from './useClassStore';

interface ClassroomDetailStore {
  classroom: Classroom | null;
  subject: Subject | null;
  students: User[];
  loading: boolean;
  error: string | null;
  fetchClassroomDetail: (id: string) => Promise<void>;
  addStudent: (id: string, studentId: string) => Promise<void>;
  removeStudent: (id: string, studentId: string) => Promise<void>;
}

export const useClassroomDetailStore = create<ClassroomDetailStore>((set) => ({
  classroom: null,
  subject: null,
  students: [],
  loading: false,
  error: null,

  fetchClassroomDetail: async (id) => {
    set({ loading: true, error: null });
    try {
      const { data: classroom } = await api.get<Classroom>(ApiConstant.classrooms.getById.replace(':id', id));
      const { data } = await api.get<User[]>(ApiConstant.classrooms.getStudents.replace(':id', classroom.id));
      const classes = await useClassStore.getState().fetchClasses();
      const students = data.map((student) => ({
        ...student,
        class: classes.find((c) => c.id === student.classId),
      }));
      const { data: subject } = await api.get<Subject>(
        ApiConstant.subjects.getById.replace(':id', classroom.subjectId),
      );
      set({ classroom, subject, students });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },

  addStudent: async (id, username) => {
    set({ loading: true, error: null });
    try {
      await api.post(ApiConstant.classrooms.addStudent.replace(':id', id), username);
      const { data: students } = await api.get<User[]>(ApiConstant.classrooms.getStudents.replace(':id', id));
      set({ students });
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },

  removeStudent: async (id, studentId) => {
    set({ loading: true, error: null });
    try {
      await api.delete(
        ApiConstant.classrooms.removeStudent.replace(':classroomId', id).replace(':studentId', studentId),
      );
      set((state) => ({ students: state.students.filter((s) => s.id !== studentId) }));
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },
}));
