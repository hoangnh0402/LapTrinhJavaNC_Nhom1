/* eslint-disable @typescript-eslint/no-explicit-any */
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import TablePage from '@/components/ui/data-table';
import Loading from '@/components/ui/loading';
import { Table, TableBody, TableCell, TableRow } from '@/components/ui/table';
import { getClassroomById } from '@/services/classroom.api';
import { fetchById } from '@/services/enrollment.api';
import { fetchStudents } from '@/services/student.api';
import { fetchSubjects } from '@/services/subject.api';
import { Classroom } from '@/types/classroom.type';
import { Enrollment } from '@/types/enrollment.type';
import { Subject } from '@/types/subject.type';
import { columns, transformData } from './columns';
import UpdateScoreModal from './score-modal';

const ClassroomDetail = () => {
  const { classroomCode } = useParams<{ classroomCode: string }>();
  const [data, setData] = useState<any[]>([]);
  const [subject, setSubject] = useState<Subject>();

  const [loading, setLoading] = useState<boolean>(true);

  const [classroom, setClassroom] = useState<Classroom>();
  const [isOpen, setIsOpen] = useState(false);
  const [selectedEnrollment, setSelectedEnrollment] = useState<Enrollment | null>(null);

  const handleOpenModal = (enrollment: Enrollment) => {
    setSelectedEnrollment(enrollment);
    setIsOpen(true);
  };

  const handleCloseModal = () => {
    setSelectedEnrollment(null);
    setIsOpen(false);
  };
  const getData = async () => {
    try {
      setLoading(true);
      if (classroomCode) {
        const enrollments = await fetchById(classroomCode);
        const students = await fetchStudents();
        const transformedData = transformData(enrollments, students);
        setData(transformedData);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    const getDataSubject = async () => {
      try {
        setLoading(true);
        const subjects = await fetchSubjects();
        const classroom = await getClassroomById(classroomCode as string);
        setClassroom(classroom);
        const subject = subjects.find((subject) => subject.id === classroom.subjectId);
        setSubject(subject);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    getData();
    getDataSubject();
  }, [classroomCode]);

  if (loading) {
    return <Loading />;
  }

  return (
    <>
      <TablePage<Enrollment>
        title={`Danh sách lớp ${classroom?.code}`}
        data={data as Enrollment[]}
        columns={columns(handleOpenModal)}
        loading={loading}
        Modal={UpdateScoreModal}
        hasToolbar={false}
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
        <br />
      </TablePage>
      {isOpen && selectedEnrollment && (
        <UpdateScoreModal
          enrollment={selectedEnrollment}
          isOpen={isOpen}
          onSubmit={async () => {
            await getData();
            handleCloseModal();
          }}
          onClose={handleCloseModal}
          classroomId={classroomCode as string}
        />
      )}
    </>
  );
};

export default ClassroomDetail;
