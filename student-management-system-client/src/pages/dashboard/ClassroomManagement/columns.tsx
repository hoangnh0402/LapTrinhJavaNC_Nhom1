/* eslint-disable react-refresh/only-export-components */
// eslint-disable-next-line react-refresh/only-export-components
import { Link } from 'react-router-dom';

import { Button } from '@/components/ui/button';
import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { DeleteDialog } from '@/components/ui/delete-dialog';
import { DialogTrigger } from '@/components/ui/dialog';
import { toast } from '@/hooks/use-toast';
import { useClassroomStore } from '@/store/useClassroomStore';
import { Classroom } from '@/types/classroom.type';
import { Dialog } from '@radix-ui/react-dialog';
import { ColumnDef } from '@tanstack/react-table';
import { format } from 'date-fns';
import { z } from 'zod';
import ClassroomModal, { FormSchema } from './classroom-modal';

const EditAction: React.FC<{ classroom: Classroom }> = ({ classroom }) => {
  const { updateClassroom } = useClassroomStore();
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button onSelect={() => {}}>Sửa</Button>
      </DialogTrigger>
      <ClassroomModal
        modalProps={{
          mode: 'edit',
          onSubmit: async (data: z.infer<typeof FormSchema>) => {
            try {
              await updateClassroom(classroom.id, data);
              toast({
                title: 'Sửa thông tin lớp học thành công',
                description: 'Thông tin lớp học đã được cập nhật',
                variant: 'success',
                duration: 2000,
              });
            } catch (error) {
              toast({
                title: 'Sửa thông tin lớp học thất bại',
                description: 'Vui lòng thử lại sau',
                variant: 'destructive',
                duration: 2000,
              });
            }
          },
        }}
        classroom={classroom}
      />
    </Dialog>
  );
};

const DeleteAction: React.FC<{ classroom: Classroom }> = ({ classroom }) => {
  const { deleteClassroom } = useClassroomStore();
  return (
    <DeleteDialog
      title="Xóa"
      onConfirm={() => {
        deleteClassroom(classroom.id);
        toast({
          title: 'Xóa lớp học thành công',
          description: 'Lớp học đã được xóa khỏi hệ thống',
          variant: 'success',
          duration: 2000,
        });
      }}
    />
  );
};

export const columns: ColumnDef<Classroom>[] = [
  {
    accessorKey: 'No',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    cell: ({ row }) => <div>{row.index + 1}</div>,
    size: 30,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'code',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mã lớp" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    cell: ({ row }) => {
      const classroom = row.original;
      return (
        <Link className="text-blue-500 underline" to={`/admin/classrooms/${classroom.id}`}>
          {classroom.code}
        </Link>
      );
    },
    enableSorting: false,
  },
  {
    accessorKey: 'room',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Phòng học" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    enableSorting: false,
  },
  {
    accessorKey: 'schedule',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Lịch học" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    enableSorting: false,
  },
  {
    accessorKey: 'startDate',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Ngày bắt đầu" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    cell: ({ row }) => {
      const startDate = row.original.startDate || '';
      if (!startDate || isNaN(new Date(startDate).getTime())) {
        return <div></div>;
      }
      return <div>{format(new Date(startDate), 'dd/MM/yyyy')}</div>;
    },
    enableSorting: false,
  },

  {
    accessorKey: 'edit-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <EditAction classroom={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
  {
    accessorKey: 'delete-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <DeleteAction classroom={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
];
