import { useEffect } from 'react';
import TablePage from '@/components/ui/data-table';
import { Subject } from '@/types/subject.type';
import { columns } from './columns';
import { useSubjectStore } from '@/store/useSubjectStore';

const SeeDocument = () => {
  const { subjects, loading, fetchSubjects } = useSubjectStore();
  useEffect(() => {
    fetchSubjects();
  }, [fetchSubjects]);

  return <TablePage<Subject> title="Danh sách học phần" data={subjects} columns={columns} loading={loading} />;
};

export default SeeDocument;
