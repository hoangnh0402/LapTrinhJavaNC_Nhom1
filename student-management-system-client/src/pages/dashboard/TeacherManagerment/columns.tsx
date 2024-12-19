/* eslint-disable react-refresh/only-export-components */
// @ts-nocheck

import { Button } from '@/components/ui/button';
import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { DeleteDialog } from '@/components/ui/delete-dialog';
import { DialogTrigger } from '@/components/ui/dialog';
import { User } from '@/types/user.type';
import { Dialog } from '@radix-ui/react-dialog';
import { ColumnDef } from '@tanstack/react-table';
import TeacherModal, { FormSchema, handleFormData } from './teacher-modal';
import { useTeacherStore } from '@/store/useTeacherStore';
import { z } from 'zod';
import { toast } from '@/hooks/use-toast';

const EditAction: React.FC<{ teacher: User }> = ({ teacher }) => {
  const { updateTeacher } = useTeacherStore();
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button>Sửa</Button>
      </DialogTrigger>
      <TeacherModal
        modalProps={{
          mode: 'edit',
          onSubmit: async (data: z.infer<typeof FormSchema>) => {
            try {
              const formData = await handleFormData(data);
              await updateTeacher(teacher.id, formData);
              toast({
                title: 'Sửa thông tin giảng viên thành công',
                description: 'Thông tin giảng viên đã được cập nhật',
                variant: 'success',
                duration: 2000,
              });
            } catch (error) {
              toast({
                title: 'Sửa thông tin giảng viên thất bại',
                description: 'Vui lòng thử lại sau',
                variant: 'destructive',
                duration: 2000,
              });
            }
          },
        }}
        teacher={teacher}
      />
    </Dialog>
  );
};

const DeleteAction: React.FC<{ teacher: User }> = ({ teacher }) => {
  const { deleteTeacher } = useTeacherStore();
  return (
    <DeleteDialog
      title="Xóa"
      onConfirm={() => {
        deleteTeacher(teacher.id);
        toast({
          title: 'Xóa giảng viên thành công',
          description: 'Giảng viên đã được xóa khỏi hệ thống',
          variant: 'success',
          duration: 2000,
        });
      }}
    />
  );
};

export const columns: ColumnDef<User>[] = [
  {
    accessorKey: 'avatar',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Ảnh" />,
    cell: ({ row }) => {
      const user = row.original;
      return (
        <img
          src={user.avatar ? user.avatar : 'https://placehold.co/140x100?text='}
          alt="Ảnh giảng viên"
          className="h-[140px] w-[100px] border border-black object-cover"
        />
      );
    },
    enableSorting: false,
    enableGlobalFilter: false,
    size: 80,
  },
  {
    accessorKey: 'username',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã giảng viên" />,
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
    accessorKey: 'phoneNumber',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Số điện thoại" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
  },
  {
    accessorKey: 'email',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Email" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
  },
  {
    accessorKey: 'edit-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <EditAction teacher={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
  {
    accessorKey: 'delete-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <DeleteAction teacher={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
];
