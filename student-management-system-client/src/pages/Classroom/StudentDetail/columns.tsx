import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { Classroom } from '@/types/classroom.type';
import { Enrollment } from '@/types/enrollment.type';
import { Subject } from '@/types/subject.type';
import { User } from '@/types/user.type';
import { ColumnDef } from '@tanstack/react-table';

export const transformData = async (
  subjects: Subject[],
  classrooms: Classroom[],
  enrollments: Enrollment[],
  user: User | null,
) => {
  return Promise.all(
    classrooms.map(async (classroom) => {
      const enrollment = enrollments.find((enrollment) => enrollment.userID === user?.id);
      const subject = subjects.find((subject) => subject.id === classroom.subjectId);
      return {
        subjectName: subject?.name || '',
        classroomCode: classroom.code,
        firstRegularPoint: enrollment?.firstRegularPoint || 0,
        secondRegularPoint: enrollment?.secondRegularPoint || 0,
        midTermPoint: enrollment?.midTermPoint || 0,
        finalPoint: enrollment?.finalPoint || 0,
      };
    }),
  );
};

// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const columns: ColumnDef<Enrollment>[] = [
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
    accessorKey: 'classroomCode',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã lớp" />,
    cell: ({ row }) => <div>{row.original.classroomCode}</div>,
  },

  {
    accessorKey: 'firstRegularPoint',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Điểm TX 1" />,
    cell: ({ row }) => <div>{row.original.firstRegularPoint}</div>,
    size: 120,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'secondRegularPoint',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Điểm TX 2" />,
    cell: ({ row }) => <div>{row.original.secondRegularPoint}</div>,
    size: 120,
    enableSorting: false,
    enableGlobalFilter: false,
  },

  {
    accessorKey: 'midTermPoint',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Điểm giữa kỳ" />,
    cell: ({ row }) => <div>{row.original.midTermPoint}</div>,
    size: 120,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'finalPoint',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Điểm cuối kỳ" />,
    cell: ({ row }) => <div>{row.original.finalPoint}</div>,
    size: 120,
    enableSorting: false,
    enableGlobalFilter: false,
  },
];
