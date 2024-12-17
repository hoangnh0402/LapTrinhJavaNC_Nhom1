import { create } from 'zustand';

import { ApiConstant, ApiConstantUser } from '@/constants/api.constant';
import { api } from '@/services/api.service';
import { Subject } from '@/types/subject.type';

import { Classroom } from '@/types/classroom.type';
import { SummaryInterface } from '@/pages/summary/summary-table/summary';

interface SummaryData {
  label: string;
  total: number;
}

interface SummaryStore {
  summaryData: SummaryInterface[];
  loading: boolean;
  error: string | null;
  fetchSummaryData: (departmentId: String | undefined) => Promise<void>;
}

export const useSummaryStore = create<SummaryStore>((set) => ({
  summaryData: [],
  loading: false,
  error: null,

  fetchSummaryData: async (departmentId: String | undefined) => {
    set({ loading: true, error: null });
    try {
      const { data: totalExcellent } = await api.get(ApiConstant.records.getTotal, {
        params: { rating: 'xuất sắc', departmentName: departmentId },
      });
      const { data: totalGood } = await api.get(ApiConstant.records.getTotal, {
        params: { rating: 'giỏi', departmentName: departmentId },
      });
      const { data: totalRather } = await api.get(ApiConstant.records.getTotal, {
        params: { rating: 'khá', departmentName: departmentId },
      });
      const { data: totalMedium } = await api.get(ApiConstant.records.getTotal, {
        params: { rating: 'trung bình', departmentName: departmentId },
      });
      const { data: totalWeak } = await api.get(ApiConstant.records.getTotal, {
        params: { rating: 'yếu', departmentName: departmentId },
      });

      let summaryData = [
        {
          point: 'Xuất sắc',
          total: totalExcellent,
        },
        {
          point: 'Giỏi',
          total: totalGood,
        },
        {
          point: 'Khá',
          total: totalRather,
        },
        {
          point: 'Trung bình',
          total: totalMedium,
        },
        {
          point: 'Yếu/Kém',
          total: totalWeak,
        },
      ];

      set({ summaryData: summaryData });
    } catch (error) {
      set({ error: error.message });
    } finally {
      set({ loading: false });
    }
  },
}));
