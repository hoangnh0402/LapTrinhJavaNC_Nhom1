import { useEffect } from 'react';
import { useParams } from 'react-router-dom';

import TablePage from '@/components/ui/data-table';
import { Table, TableBody, TableCell, TableRow } from '@/components/ui/table';
import { User } from '@/types/user.type';
import AddStudentClassroomModal from './add-student-classroom-modal';
import { columns } from './columns';
import { useClassroomDetailStore } from '@/store/useClassroomDetailStore';
import Loading from '@/components/ui/loading';

const ClassroomDetailManagement = () => {
  const { id } = useParams<{ id: string }>();

  const { classroom, students, subject, loading, fetchClassroomDetail } = useClassroomDetailStore();

  useEffect(() => {
    if (id) {
      fetchClassroomDetail(id);
    }
  }, [id, fetchClassroomDetail]);

  if (loading) {
    return <Loading />;
  }

  return (
    <TablePage<User>
      title="Quản lý sinh viên trong lớp học"
      data={students}
      columns={columns}
      Modal={AddStudentClassroomModal}
      loading={loading}
      hasPagination={false}
    >
      <div>
        <Table className="mt-4 border">
          <TableBody className="bg-white *:*:w-1/4 *:*:border *:*:p-3">
            <TableRow>
              <TableCell>Môn:</TableCell>
              <TableCell>{subject?.name}</TableCell>
              <TableCell>Trình độ:</TableCell>
              <TableCell></TableCell>
            </TableRow>
            <TableRow>
              <TableCell>Mã lớp độc lập:</TableCell>
              <TableCell>{classroom?.code}</TableCell>
              <TableCell>Số tín chỉ:</TableCell>
              <TableCell>{subject?.numberOfCredits}</TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </div>
    </TablePage>
  );
};

export default ClassroomDetailManagement;
