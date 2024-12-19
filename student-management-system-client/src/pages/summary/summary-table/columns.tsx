import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';

import { Subject } from '@/types/subject.type';

import { ColumnDef } from '@tanstack/react-table';
import { SummaryInterface } from './summary';

export const columns: ColumnDef<SummaryInterface>[] = [
  {
    accessorKey: 'No',
    header: ({ column }) => <DataTableColumnHeader column={column} title="STT" />,
    cell: ({ row }) => <div>{row.index + 1}</div>,
    size: 30,
    enableSorting: false,
    enableGlobalFilter: false,
  },
  {
    accessorKey: 'point',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Điểm" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 120,
  },
  {
    accessorKey: 'total',
    header: ({ column }) => <DataTableColumnHeader column={column} title="Số lượng" />,
    filterFn: (row, id, value) => {
      return value.includes(row.getValue(id));
    },
    size: 70,
    enableSorting: false,
  },
];
