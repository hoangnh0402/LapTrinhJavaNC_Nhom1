/* eslint-disable react-refresh/only-export-components */
// eslint-disable-next-line react-refresh/only-export-components

import { DataTableColumnHeader } from '@/components/ui/data-table/data-table-column-header';
import { ColumnDef } from '@tanstack/react-table';
import { SummaryInterface } from './summary-table/summary';

export const registeredSubjectTableColumn: ColumnDef<SummaryInterface>[] = [
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
    size: 120,
  },
];
