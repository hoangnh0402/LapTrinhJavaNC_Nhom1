import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog';
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { updateScore } from '@/services/enrollment.api';
import { UpdateScoreModalProps } from '@/types/enrollment.type';

const FormSchema = z.object({
  firstRegularPoint: z.coerce.number().min(0).max(10),
  secondRegularPoint: z.coerce.number().min(0).max(10),
  midTermPoint: z.coerce.number().min(0).max(10),
  finalPoint: z.coerce.number().min(0).max(10),
});

const UpdateScoreModal = ({ enrollment, isOpen, onClose, classroomId, onSubmit }: UpdateScoreModalProps) => {
  const form = useForm<z.infer<typeof FormSchema>>({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      firstRegularPoint: enrollment?.firstRegularPoint,
      secondRegularPoint: enrollment?.secondRegularPoint,
      midTermPoint: enrollment?.midTermPoint,
      finalPoint: enrollment?.finalPoint,
    },
  });

  const handleSubmit = async (data: z.infer<typeof FormSchema>) => {
    try {
      const requestBody = {
        userId: enrollment?.userID,
        classroomId: classroomId,
        firstRegularPoint: data.firstRegularPoint,
        secondRegularPoint: data.secondRegularPoint,
        midTermPoint: data.midTermPoint,
        finalPoint: data.finalPoint,
      };

      await updateScore(requestBody);
      alert('Cập nhật điểm thành công');
      onSubmit(requestBody); // Gọi callback với dữ liệu
    } catch (error) {
      alert('Cập nhật điểm thất bại');
      console.error('Error:', error);
    }
  };

  return (
    <Dialog open={isOpen} onOpenChange={(open) => !open && onClose()}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Cập nhật điểm</DialogTitle>
          <DialogDescription>Chỉnh sửa điểm số</DialogDescription>
        </DialogHeader>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(handleSubmit)}>
            <FormField
              control={form.control}
              name="firstRegularPoint"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Điểm TX 1</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="secondRegularPoint"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Điểm TX 2</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="midTermPoint"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Điểm giữa kỳ</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="finalPoint"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Điểm cuối kỳ</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <DialogFooter>
              <Button type="submit">Lưu</Button>
              <Button type="button" onClick={onClose} variant="outline">
                Hủy
              </Button>
            </DialogFooter>
          </form>
        </Form>
      </DialogContent>
    </Dialog>
  );
};

export default UpdateScoreModal;
