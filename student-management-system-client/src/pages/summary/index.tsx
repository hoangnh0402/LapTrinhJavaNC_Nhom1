import * as React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { LabelList, Pie, PieChart } from 'recharts';
import {
  ChartContainer,
  ChartLegend,
  ChartLegendContent,
  ChartTooltip,
  ChartTooltipContent,
} from '@/components/ui/chart';
import ComboBox from './combo-box';
import TablePage from '@/components/ui/data-table';
import { frameworks, chartConfig, summaryData } from './mock-data';
import { ChartInterface, SummaryInterface } from './summary-table/summary';
import { columns } from './summary-table/columns';
import { useSummaryStore } from '@/store/useSummaryStore';

const Summary = () => {
  const [departmentId, setDepartmentId] = React.useState<string>('78a3dd94-a78b-4908-8c5e-bd6c2ab57954');

  const { summaryData, loading, error, fetchSummaryData } = useSummaryStore();

  // Correctly use useState with a type assertion and default empty array
  const [chartData, setChartData] = React.useState<ChartInterface[]>([]);

  React.useEffect(() => {
    fetchSummaryData(departmentId);

    // Only set chart data if summaryData is not empty
    if (summaryData && summaryData.length > 0) {
      setChartData([
        { point: 'excellent', total: summaryData[0].total, fill: 'var(--color-excellent)' },
        { point: 'good', total: summaryData[1].total, fill: 'var(--color-good)' },
        { point: 'rather', total: summaryData[2].total, fill: 'var(--color-rather)' },
        { point: 'medium', total: summaryData[3].total, fill: 'var(--color-medium)' },
        { point: 'weak', total: summaryData[4].total, fill: 'var(--color-weak)' },
      ]);
    }
  }, [departmentId]);

  return (
    <Card>
      <CardHeader>
        <CardTitle className="text-2xl">Thống kê điểm tích lũy</CardTitle>
      </CardHeader>
      <CardContent>
        <ComboBox data={frameworks} value={departmentId} setValue={setDepartmentId} />

        <div className="mt-4 grid grid-cols-2 gap-4">
          <div className="flex flex-col items-center justify-center">
            <ChartContainer config={chartConfig} className="aspect-square max-h-[400px] w-full">
              <PieChart>
                <ChartTooltip content={<ChartTooltipContent nameKey="total" hideLabel />} />
                <Pie data={chartData} dataKey="total" nameKey="point" className="fill-primary">
                  <LabelList
                    dataKey="point"
                    className="fill-white text-[16px] font-bold text-white drop-shadow-[1px_0_0_rgba(0,0,0,0.5)]"
                    stroke="none"
                    fontSize={12}
                    formatter={(value: keyof typeof chartConfig) => chartConfig[value]?.label}
                  />
                </Pie>
                <ChartLegend content={<ChartLegendContent nameKey="point" />} className="" />
              </PieChart>
            </ChartContainer>
          </div>
          <div>
            <TablePage<SummaryInterface>
              title=""
              data={summaryData}
              columns={columns}
              hasPagination={false}
              hasToolbar={false}
              loading={loading}
            />
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default Summary;
