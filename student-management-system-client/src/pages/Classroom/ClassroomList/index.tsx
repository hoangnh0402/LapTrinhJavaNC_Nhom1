import React, { useEffect, useState } from 'react';
import TablePage from '@/components/ui/data-table';
import Loading from '@/components/ui/loading';
import { fetchClassrooms } from '@/services/classroom.api';
import { fetchSubjects } from '@/services/subject.api';
import { columns, transformData } from './columns';

const SubjectList: React.FC = () => {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const [data, setData] = useState<any[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const getData = async () => {
      try {
        const subjects = await fetchSubjects();
        const classrooms = await fetchClassrooms();
        const transformedData = transformData(subjects, classrooms);
        setData(transformedData);
      } catch (error) {
        console.error('Error fetching data:', error);
      } finally {
        setLoading(false);
      }
    };

    getData();
  }, []);

  if (loading) {
    return <Loading />;
  }

  return (
    <div>
      <TablePage data={data} columns={columns} title="Danh sách các lớp học" />
    </div>
  );
};

export default SubjectList;
