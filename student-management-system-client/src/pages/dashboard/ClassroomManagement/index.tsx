import { useEffect } from 'react';

import TablePage from '@/components/ui/data-table';
import { Classroom } from '@/types/classroom.type';
import ClassroomModal from './classroom-modal';
import { columns } from './columns';
import { useClassroomStore } from '@/store/useClassroomStore';

const ClassroomManagement = () => {
  const { classrooms, loading, fetchClassrooms } = useClassroomStore();

  useEffect(() => {
    fetchClassrooms();
  }, [fetchClassrooms]);

  return (
    <TablePage<Classroom>
      title="Quản lý lớp học"
      data={classrooms}
      columns={columns}
      Modal={ClassroomModal}
      loading={loading}
    />
  );
};

export default ClassroomManagement;
