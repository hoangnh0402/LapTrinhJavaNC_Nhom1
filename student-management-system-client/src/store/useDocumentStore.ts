import { create } from 'zustand';

import { ApiConstant } from '@/constants/api.constant';
import { api, apiDefaultUpload } from '@/services/api.service';
import { Document } from '@/types/document.type';
interface DocumentStore {
  documents: Document[];
  document: Document | null;
  loading: boolean;
  error: string | null;
  fetchDocumentBySubject: (id: string) => Promise<void>;
  getDocumentById: (id: string) => Promise<void>;
  uploadDocument: (data: FormData) => Promise<void>;
}

export const useDocumentStore = create<DocumentStore>((set) => ({
  documents: [],
  document: null,
  loading: false,
  error: null,

  fetchDocumentBySubject: async (id) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.get<Document[]>(`${ApiConstant.documents.getAllBySubject}?subjectId=${id}`);
      set({ documents: data });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },
  getDocumentById: async (id) => {
    set({ loading: true, error: null });
    try {
      const { data } = await api.get<Document>(`${ApiConstant.documents.getById}?id=${id}`);
      set({ document: data });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },

  uploadDocument: async (formData) => {
    set({ loading: true, error: null });
    try {
      await apiDefaultUpload.post(ApiConstant.documents.upload, formData);
    } catch (error) {
      set({ error: error.message });
      throw error;
    } finally {
      set({ loading: false });
    }
  },
}));
