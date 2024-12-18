import { useEffect } from 'react';

import TablePage from '@/components/ui/data-table';
import { useSubjectStore } from '@/store/useSubjectStore';
import { Subject } from '@/types/subject.type';
import { columns } from './columns';
import SubjectModal from './subject-modal';

const SubjectMangement = () => {
  const { subjects, loading, fetchSubjects } = useSubjectStore();

  useEffect(() => {
    fetchSubjects();
  }, [fetchSubjects]);

  return (
    <TablePage<Subject>
      title="Quản lý môn học"
      data={subjects}
      columns={columns}
      Modal={SubjectModal}
      loading={loading}
    />
  );
};

export default SubjectMangement;
