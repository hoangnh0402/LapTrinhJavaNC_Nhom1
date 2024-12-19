import { ChartConfig } from '@/components/ui/chart';

export const frameworks = [
  {
    value: '012f904e-dcc5-44a7-b483-8f2630d8f6b9',
    label: 'EE',
  },
  {
    value: '34fa5034-2858-4331-86f5-b35d6f5bae4c',
    label: 'LPS',
  },
  {
    value: '4e1d0e87-867b-489c-8c53-e96ace805e54',
    label: 'SLT',
  },
  {
    value: '5f88e528-ea9b-4333-9ffc-33b52236c77e',
    label: 'SMAE',
  },
  {
    value: '78a3dd94-a78b-4908-8c5e-bd6c2ab57954',
    label: 'FIT',
  },
  {
    value: '84c1962a-35b5-4d5f-9b8f-857b00853c0d',
    label: 'FCT',
  },
  {
    value: '9865cdc7-bce2-4bd9-9933-c0ea7ac76c01',
    label: 'FAA',
  },
  {
    value: 'bda613a4-7712-4c94-bed1-929ad545af6c',
    label: 'HIT',
  },
  {
    value: 'd9bb5faa-a264-46d9-b97e-9c913e1cdb34',
    label: 'FBM',
  },
  {
    value: 'e7654861-9290-4dfb-a842-e01375b863a1',
    label: 'FFS',
  },
  {
    value: 'ed6286de-529d-4d37-97f8-5515ec26e1d9',
    label: 'FEE',
  },
  {
    value: 'f748b5ed-e2cc-4b59-bd58-df12fcda3777',
    label: 'FGD',
  },
];

export const chartConfig = {
  total: {
    label: 'Số sinh viên',
  },
  excellent: {
    label: 'Xuất sắc',
    color: 'hsl(var(--chart-1))',
  },
  good: {
    label: 'Giỏi',
    color: 'hsl(var(--chart-2))',
  },
  rather: {
    label: 'Khá',
    color: 'hsl(var(--chart-3))',
  },
  medium: {
    label: 'Trung bình',
    color: 'hsl(var(--chart-4))',
  },
  weak: {
    label: 'Yếu kém',
    color: 'hsl(var(--chart-5))',
  },
  // Dplus: {
  //   label: "D+",
  //   color: "hsl(var(--chart-6))",
  // },
  // D: {
  //   label: "D",
  //   color: "hsl(var(--chart-7))",
  // },
  // F: {
  //   label: "F",
  //   color: "hsl(var(--chart-8))",
  // },
} satisfies ChartConfig;

export const chartData = [
  { point: 'excellent', total: 275, fill: 'var(--color-excellent)' },
  { point: 'good', total: 200, fill: 'var(--color-good)' },
  { point: 'rather', total: 187, fill: 'var(--color-rather)' },
  { point: 'medium', total: 173, fill: 'var(--color-medium)' },
  { point: 'weak', total: 90, fill: 'var(--color-weak)' },
  // { point: 'Dplus', total: 173, fill: 'var(--color-Dplus)' },
  // { point: 'D', total: 187, fill: 'var(--color-D)' },
  // { point: 'F', total: 90, fill: 'var(--color-F)' },
];

export const summaryData = [
  {
    point: 'Xuất sắc',
    total: 444,
  },
  {
    point: 'Giỏi',
    total: 444,
  },
  {
    point: 'Khá',
    total: 3,
  },
  {
    point: 'Trung bình',
    total: 72214,
  },
  {
    point: 'Yếu/Kém',
    total: 33,
  },
  // {
  //   "point": "D+",
  //   "total": 507
  // }, {
  //   "point": "D",
  //   "total": 604
  // }, {
  //   "point": "F",
  //   "total": 110
  // }
];
