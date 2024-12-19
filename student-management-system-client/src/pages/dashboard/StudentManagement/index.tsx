import { useEffect } from 'react';

import TablePage from '@/components/ui/data-table';
import { useClassStore } from '@/store/useClassStore';
import { useStudentStore } from '@/store/useStudentStore';
import { User } from '@/types/user.type';
import { columns } from './columns';
import StudentModal from './student-modal';

const StudentManagement = () => {
  const { students, loading, fetchStudents } = useStudentStore();
  const { fetchClasses } = useClassStore();

  useEffect(() => {
    fetchStudents();
    fetchClasses();
  }, [fetchStudents, fetchClasses]);

  return (
    <TablePage<User>
      title="Quản lý sinh viên"
      data={students}
      columns={columns}
      Modal={StudentModal}
      loading={loading}
    />
  );
};

export default StudentManagement;
