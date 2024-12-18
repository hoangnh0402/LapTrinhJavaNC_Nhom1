import { Button } from '@/components/ui/button';
import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { Subject } from '@/types/subject.type';
import { ColumnDef } from '@tanstack/react-table';
import { useNavigate } from 'react-router-dom';

const DocumentsButton = ({ subject }: { subject: Subject }) => {
  const navigate = useNavigate();

  const handleViewDocuments = () => {
    navigate(`/documents/${subject.id}`);
  };

  return <Button onClick={handleViewDocuments}>Xem tài liệu</Button>;
};

export const columns: ColumnDef<Subject>[] = [
  {
    accessorKey: 'id',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    cell: ({ row }) => <div>{row.index + 1}</div>,
    size: 30,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'name',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Tên môn" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 120,
  },
  {
    accessorKey: 'code',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã môn" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 80,
    enableSorting: false,
  },
  {
    accessorKey: 'numberOfCredits',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Số tín chỉ" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 70,
    enableSorting: false,
  },
  {
    accessorKey: 'description',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mô tả" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    enableSorting: false,
  },
  {
    accessorKey: 'prerequisiteSubjects',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Môn tiên quyết" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 70,
    enableSorting: false,
  },
  {
    accessorKey: 'documents',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Xem tài liệu" />,
    cell: ({ row }) => {
      const subject = row.original;
      return <DocumentsButton subject={subject} />;
    },
    enableSorting: false,
    enableHiding: false,
    size: 70,
  },
];
