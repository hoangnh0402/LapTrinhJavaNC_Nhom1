import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';

import { Subject } from '@/types/subject.type';
import { Classroom } from '@/types/classroom.type';
import { ColumnDef } from '@tanstack/react-table';
export const transformData = (subjects: Subject[], classrooms: Classroom[]) => {
  return classrooms.map((classroom) => {
    const subject = subjects.find((subject) => subject.id === classroom.subjectId);
    return {
      id: classroom.id,
      subjectName: subject?.name || '',
      subjectCode: subject?.code || '',
      classroomCode: classroom.code,
      numberOfCredits: subject?.numberOfCredits || 0,
      regularCoefficient: subject?.regularCoefficient || 0,
      midTermCoefficient: subject?.midTermCoefficient || 0,
      finalCoefficient: subject?.finalCoefficient || 0,
      description: subject?.description || '',
    };
  });
};
// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const columns: ColumnDef<any>[] = [
  {
    accessorKey: 'No',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    cell: ({ row }) => <div>{row.index + 1}</div>,
    size: 30,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'subjectName',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Tên môn" />,
    cell: ({ row }) => <div>{row.original.subjectName}</div>,
    size: 120,
  },
  {
    accessorKey: 'subjectCode',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã môn" />,
    cell: ({ row }) => <div>{row.original.subjectCode}</div>,

    size: 70,
    enableSorting: false,
  },
  {
    accessorKey: 'classroomCode',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã lớp" />,
    cell: ({ row }) => {
      const classroomCode = row.original.classroomCode;
      return (
        <a href={`/classrooms/${row.original.id}`} style={{ color: 'blue', textDecoration: 'underline' }}>
          {classroomCode}
        </a>
      );
    },
    size: 70,
    enableSorting: false,
  },
  {
    accessorKey: 'numberOfCredits',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Số tín chỉ" />,
    cell: ({ row }) => <div>{row.original.numberOfCredits}</div>,
    size: 70,
  },
  {
    accessorKey: 'regularCoefficient',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hệ số thường xuyên" />,
    cell: ({ row }) => <div>{row.original.regularCoefficient}</div>,
    size: 80,
  },
  {
    accessorKey: 'midTermCoefficient',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hệ số giữa kì" />,
    cell: ({ row }) => <div>{row.original.midTermCoefficient}</div>,
    size: 80,
  },
  {
    accessorKey: 'finalCoefficient',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hệ số cuối kì" />,
    cell: ({ row }) => <div>{row.original.finalCoefficient}</div>,
    size: 80,
  },
  {
    accessorKey: 'description',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mô tả" />,
    cell: ({ row }) => <div>{row.original.description}</div>,
    size: 120,
  },
];
