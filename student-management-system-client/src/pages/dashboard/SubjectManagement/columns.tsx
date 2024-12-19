/* eslint-disable react-refresh/only-export-components */
// eslint-disable-next-line react-refresh/only-export-components

import { Button } from '@/components/ui/button';
import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { DeleteDialog } from '@/components/ui/delete-dialog';
import { DialogTrigger } from '@/components/ui/dialog';
import { Subject } from '@/types/subject.type';
import { Dialog } from '@radix-ui/react-dialog';
import { ColumnDef } from '@tanstack/react-table';
import SubjectModal, { FormSchema } from './subject-modal';
import { useSubjectStore } from '@/store/useSubjectStore';
import { z } from 'zod';
import { toast } from '@/hooks/use-toast';

const EditAction: React.FC<{ subject: Subject }> = ({ subject }) => {
  const { updateSubject } = useSubjectStore();
  return (
    <Dialog>
      <DialogTrigger asChild>
        <Button>Sửa</Button>
      </DialogTrigger>
      <SubjectModal
        modalProps={{
          mode: 'edit',
          onSubmit: async (data: z.infer<typeof FormSchema>) => {
            try {
              await updateSubject(subject.id, data);
              toast({
                title: 'Sửa thông tin môn học thành công',
                description: 'Thông tin môn học đã được cập nhật',
                variant: 'success',
                duration: 2000,
              });
            } catch (error) {
              toast({
                title: 'Sửa thông tin môn học thất bại',
                description: 'Vui lòng thử lại sau',
                variant: 'destructive',
                duration: 2000,
              });
            }
          },
        }}
        subject={subject}
      />
    </Dialog>
  );
};

const DeleteAction: React.FC<{ subject: Subject }> = ({ subject }) => {
  const { deleteSubject } = useSubjectStore();
  return (
    <DeleteDialog
      title="Xóa"
      onConfirm={() => {
        deleteSubject(subject.id);
        toast({
          title: 'Xóa môn học thành công',
          description: 'Môn học đã được xóa khỏi hệ thống',
          variant: 'success',
          duration: 2000,
        });
      }}
    />
  );
};

export const columns: ColumnDef<Subject>[] = [
  {
    accessorKey: 'No',
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
    size: 70,
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
    accessorKey: 'regularCoefficient',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hệ số thường xuyên" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 80,
    enableSorting: false,
  },
  {
    accessorKey: 'midTermCoefficient',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hệ số giữa kì" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 80,
    enableSorting: false,
  },
  {
    accessorKey: 'finalCoefficient',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Hệ số cuối kì" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 80,
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
    accessorKey: 'edit-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <EditAction subject={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
  {
    accessorKey: 'delete-action',
    header: ({ column }) => <DataTableColumnHeader column={column} title="" />,
    cell: ({ row }) => <DeleteAction subject={row.original} />,
    enableSorting: false,
    enableHiding: false,
    size: 44,
  },
];
