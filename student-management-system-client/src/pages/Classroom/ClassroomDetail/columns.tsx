import { Button } from '@/components/ui/button';
import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { useUserStore } from '@/store/useUserStore';
import { Enrollment } from '@/types/enrollment.type';
import { Role, User } from '@/types/user.type';
import { ColumnDef } from '@tanstack/react-table';

const EntryPointAction: React.FC<{ enrollment: Enrollment; onOpenModal: (enrollment: Enrollment) => void }> = ({
  enrollment,
  onOpenModal,
}) => {
  const { user } = useUserStore();
  return (
    user?.roleName === Role.TEACHER && (
      <Button
        onClick={() => {
          onOpenModal(enrollment);
        }}
      >
        Nhập điểm
      </Button>
    )
  );
};

export const transformData = (enrollments: Enrollment[], users: User[]) => {
  return enrollments.map((enrollment) => {
    const user = users.find((user) => enrollment.userID === user.id);
    return {
      userID: enrollment?.userID,
      username: user?.username || '',
      fullName: user?.fullName || '',
      firstRegularPoint: enrollment?.firstRegularPoint || 0,
      secondRegularPoint: enrollment?.secondRegularPoint || 0,
      midTermPoint: enrollment?.midTermPoint || 0,
      finalPoint: enrollment?.finalPoint || 0,
    };
  });
};
// eslint-disable-next-line @typescript-eslint/no-explicit-any
export const columns = (onOpenModal: (enrollment: Enrollment) => void): ColumnDef<Enrollment>[] => [
  {
    accessorKey: 'No',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    cell: ({ row }) => <div>{row.index + 1}</div>,
    size: 30,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'userId',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã sinh viên" />,
    cell: ({ row }) => <div>{row.original.username}</div>,
    size: 100,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'username',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Tên sinh viên" />,
    cell: ({ row }) => {
      return (
        <a href={`/student/${row.original.username}`} style={{ color: 'blue', textDecoration: 'underline' }}>
          {row.original.fullName}
        </a>
      );
    },
    size: 100,
    enableSorting: false,
    enableGlobalFilter: false,
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
  {
    accessorKey: 'details',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hành động" />,
    cell: ({ row }) => <EntryPointAction enrollment={row.original} onOpenModal={onOpenModal} />,
    enableSorting: false,
    enableHiding: false,
    size: 100,
  },
];
