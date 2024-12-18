/* eslint-disable react-refresh/only-export-components */
// eslint-disable-next-line react-refresh/only-export-components
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { DeleteDialog } from '@/components/ui/delete-dialog';
import { DialogTrigger } from '@/components/ui/dialog';
import { useStudentStore } from '@/store/useStudentStore';
import { User } from '@/types/user.type';
import { Dialog } from '@radix-ui/react-dialog';
import { ColumnDef } from '@tanstack/react-table';
import StudentModal, { FormSchema } from './student-modal';
import { format } from 'date-fns';
import { toast } from '@/hooks/use-toast';

const EditAction: React.FC<{ student: User }> = ({ student }) => {
  const { updateStudent } = useStudentStore();
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button>Sửa</Button>
      </DialogTrigger>
      <StudentModal
        modalProps={{
          mode: 'edit',
          onSubmit: async (data: z.infer<typeof FormSchema>) => {
            try {
              await updateStudent(student.id, data);
              toast({
                title: 'Sửa thông tin sinh viên thành công',
                description: 'Thông tin sinh viên đã được cập nhật',
                variant: 'success',
                duration: 2000,
              });
            } catch (error) {
              toast({
                title: 'Sửa thông tin sinh viên thất bại',
                description: 'Vui lòng thử lại sau',
                variant: 'destructive',
                duration: 2000,
              });
            }
          },
        }}
        student={student}
      />
    </Dialog>
  );
};

const DeleteAction: React.FC<{ student: User }> = ({ student }) => {
  const { deleteStudent } = useStudentStore();
  return (
    <DeleteDialog
      title="Xóa"
      onConfirm={() => {
        deleteStudent(student.id);
        toast({
          title: 'Xóa sinh viên thành công',
          description: 'Sinh viên đã được xóa khỏi hệ thống',
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
  },
  {
    accessorKey: 'fullName',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Họ tên" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
  },
  {
    accessorKey: 'gender',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Giới tính" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
  },
  {
    accessorKey: 'birthday',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Ngày sinh" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    cell: ({ row }) => {
      const birthday = row.original.birthday || '';
      if (!birthday || isNaN(new Date(birthday).getTime())) {
        return <div></div>;
      }
      return <div>{format(new Date(birthday), 'dd/MM/yyyy')}</div>;
    },
  },
  {
    accessorKey: 'class.name',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Lớp" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
  },
  {
    accessorKey: 'phoneNumber',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Số điện thoại" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
  },
  {
    accessorKey: 'edit-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <EditAction student={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 50,
  },
  {
    accessorKey: 'delete-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <DeleteAction student={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 50,
  },
];
