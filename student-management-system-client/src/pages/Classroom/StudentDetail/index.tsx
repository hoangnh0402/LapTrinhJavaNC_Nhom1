/* eslint-disable @typescript-eslint/no-explicit-any */
import React, { useEffect, useState } from 'react';
import TablePage from '@/components/ui/data-table';
import { useParams } from 'react-router-dom';
import { columns, transformData } from './columns';
import { getByUsercode } from '@/services/user.api';
import { fetchById } from '@/services/enrollment.api';
import { getClassByStudent } from '@/services/classroom.api';
import { fetchSubjects } from '@/services/subject.api';
import Loading from '@/components/ui/loading';

const StudentDetail: React.FC = () => {
  const [data, setData] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const { username } = useParams();

  const getData = async () => {
    try {
      if (username) {
        const classrooms = await getClassByStudent(username);
        const subjects = await fetchSubjects();
        const user = await getByUsercode(username);
        const allEnrollments = await Promise.all(
          classrooms.map(async (classroom) => {
            const enrollments = await fetchById(classroom.id);
            return enrollments;
          }),
        );
        const flatEnrollments = allEnrollments.flat();
        const transformedData = await transformData(subjects, classrooms, flatEnrollments, user);
        setData(transformedData);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    getData();
  }, []);

  if (loading) {
    return <Loading />;
  }

  return (
    <div>
      <TablePage data={data} columns={columns} title="Chi tiết thông tin sinh viên" />
    </div>
  );
};

export default StudentDetail;
