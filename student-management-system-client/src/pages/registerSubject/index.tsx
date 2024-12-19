import { useEffect, useState } from 'react';
import TablePage from '@/components/ui/data-table';
import { Subject } from '@/types/subject.type';
import { Classroom } from '@/types/classroom.type';

import { registerSubjectTableColumn } from './registerSubjectTableColumn';
import { registeredSubjectTableColumn } from './registeredSubjectTableColumn';
import { useSubjectStore } from '@/store/useSubjectStore';
import { useClassroomStore } from '@/store/useClassroomStore';
import { AvailableRegisterSubject } from '@/types/registerSubject.type';
import { useRegisterSubjectStore } from '@/store/useRegisterSubjectStore';
import { useRegisteredSubjectStore } from '@/store/useRegisterdSubjectStore';
import { useUserStore } from '@/store/useUserStore';

const RegisterSubject = () => {
  const { user } = useUserStore();
  const { registerSubject, error, fetchRegisterSubjects } = useRegisterSubjectStore();
  const { registeredSubject, loading, fetchRegisteredSubjects } = useRegisteredSubjectStore();

  const userId: String | undefined = user?.username;
  useEffect(() => {
    fetchRegisterSubjects();
    fetchRegisteredSubjects(userId);
  }, []);

  return (
    <>
      <TablePage<AvailableRegisterSubject>
        title="Môn đã đăng kí"
        data={registeredSubject}
        columns={registeredSubjectTableColumn}
        loading={loading}
        hasToolbar={false}
      />
      <TablePage<AvailableRegisterSubject>
        title="Đăng kí học phần"
        data={registerSubject}
        columns={registerSubjectTableColumn}
        hasToolbar={false}
        loading={loading}
      />
    </>
  );
};

export default RegisterSubject;
