import TablePage from '@/components/ui/data-table';
import { Document } from '@/types/document.type';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { documentColumns } from './columns';
import DocumentModal from './document-modal';
import { useDocumentStore } from '@/store/useDocumentStore';
import { getById } from '@/services/subject.api';
import { useUserStore } from '@/store/useUserStore';
import { Role } from '@/types/user.type';

const DocumentList = () => {
  const { id } = useParams<{ id: string }>();
  const { user } = useUserStore();
  const { documents, loading, fetchDocumentBySubject } = useDocumentStore();
  const [subjectName, setSubjectName] = useState<string>('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (id) {
          await fetchDocumentBySubject(id);
          const subject = await getById(id);
          setSubjectName(subject.name);
        }
      } catch (error) {
        console.error('Failed to fetch subject:', error);
      }
    };

    fetchData();
  }, [id]);

  return (
    <div>
      <TablePage<Document>
        title={`Danh sách tài liệu thuộc học phần: ${subjectName}`}
        data={documents}
        columns={documentColumns}
        loading={loading}
        Modal={user?.roleName === Role.STUDENT ? undefined : DocumentModal}
      />
    </div>
  );
};

export default DocumentList;
