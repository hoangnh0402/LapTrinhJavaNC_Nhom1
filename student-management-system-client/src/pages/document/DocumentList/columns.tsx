import { format } from 'date-fns';

import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { Document } from '@/types/document.type';
import { ColumnDef } from '@tanstack/react-table';
import DownloadButton from './dowload-button';

export const documentColumns: ColumnDef<Document>[] = [
  {
    accessorKey: 'id',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    size: 40,
    cell: ({ row, table }) => table.getSortedRowModel().flatRows.indexOf(row) + 1,
    enableSorting: false,
  },
  {
    accessorKey: 'name',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Tên tài liệu" />,
    cell: ({ row }) => (
      <a
        href={row.original.path}
        target="_blank"
        rel="noopener noreferrer"
        className="text-blue-500 underline hover:text-blue-700"
      >
        {row.getValue('name')}
      </a>
    ),
    enableSorting: false,
  },

  {
    accessorKey: 'description',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Mô tả" />,
    enableSorting: false,
  },
  {
    accessorKey: 'type',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Loại tài liệu" />,
    enableSorting: false,
  },
  {
    accessorKey: 'createdDate',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Ngày tạo" />,
    cell: ({ row }) => {
      const createdDate = row.original.createdDate || '';
      if (!createdDate || isNaN(new Date(createdDate).getTime())) {
        return <div></div>;
      }
      return <div>{format(new Date(createdDate), 'dd/MM/yyyy')}</div>;
    },
    enableSorting: false,
  },
  {
    accessorKey: 'download',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Tải về" />,
    cell: ({ row }) => <DownloadButton documentId={row.original.id} />,
    enableSorting: false,
  },
];
