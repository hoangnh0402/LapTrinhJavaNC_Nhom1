import { useEffect } from 'react';

import TablePage from '@/components/ui/data-table';
import { useTeacherStore } from '@/store/useTeacherStore';
import { User } from '@/types/user.type';
import { columns } from './columns';
import TeacherModal from './teacher-modal';

const TeacherManagement = () => {
  const { teachers, loading, fetchTeachers } = useTeacherStore();

  useEffect(() => {
    fetchTeachers();
  }, [fetchTeachers]);

  return (
    <TablePage<User>
      title="Quản lý giảng viên"
      data={teachers}
      columns={columns}
      Modal={TeacherModal}
      loading={loading}
    />
  );
};

export default TeacherManagement;
