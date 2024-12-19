/* eslint-disable react-refresh/only-export-components */
// eslint-disable-next-line react-refresh/only-export-components
import { useParams } from 'react-router-dom';

import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { DeleteDialog } from '@/components/ui/delete-dialog';
import { toast } from '@/hooks/use-toast';
import { useClassroomDetailStore } from '@/store/useClassroomDetailStore';
import { User } from '@/types/user.type';
import { ColumnDef } from '@tanstack/react-table';

const DeleteAction: React.FC<{ user: User }> = ({ user }) => {
  const { removeStudent } = useClassroomDetailStore();
  const { id } = useParams<{ id: string }>();
  if (!id) return null;
  return (
    <DeleteDialog
      title="Xóa"
      onConfirm={() => {
        removeStudent(id, user.id);
        toast({
          title: 'Xóa sinh viên khỏi lớp học thành công',
          description: 'Sinh viên đã được xóa khỏi lớp học',
          variant: 'success',
          duration: 2000,
        });
      }}
    />
  );
};

export const columns: ColumnDef<User>[] = [
  {
    accessorKey: 'No',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    cell: ({ row }) => <div>{row.index + 1}</div>,
    size: 30,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'username',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã sinh viên" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },

    enableSorting: false,
  },
  {
    accessorKey: 'fullName',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Họ tên" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    enableSorting: false,
  },
  {
    accessorKey: 'class.name',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Lớp hành chính" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    enableSorting: false,
  },
  {
    accessorKey: 'absence',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Số tiết nghỉ" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    cell: () => <div>0</div>,
    enableSorting: false,
  },
  {
    accessorKey: 'examCondition',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Điều kiện dự thi" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    cell: () => <div>Đủ điều kiện</div>,
    enableSorting: false,
  },
  {
    accessorKey: 'delete-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <DeleteAction user={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
];
