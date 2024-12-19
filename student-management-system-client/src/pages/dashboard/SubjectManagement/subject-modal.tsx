import { zodResolver } from '@hookform/resolvers/zod';
import { useForm } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import { DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { Subject } from '@/types/subject.type';
import { useSubjectStore } from '@/store/useSubjectStore';
import { toast } from '@/hooks/use-toast';

interface SubjectModalProps {
  modalProps?: {
    mode: 'read' | 'create' | 'edit';
    onSubmit: (data: z.infer<typeof FormSchema>) => void;
  };
  subject?: Subject;
  onClose?: () => void;
}

export const FormSchema = z.object({
  name: z.string({ required_error: 'Tên môn học không được để trống' }),
  code: z.string({ required_error: 'Mã môn học không được để trống' }),
  numberOfCredits: z.coerce.number({ required_error: 'Số tín chỉ không được để trống' }),
  regularCoefficient: z.coerce.number({ required_error: 'Hệ số thường xuyên không được để trống' }),
  midTermCoefficient: z.coerce.number({ required_error: 'Hệ số giữa kì không được để trống' }),
  finalCoefficient: z.coerce.number({ required_error: 'Hệ số cuối kì không được để trống' }),
  description: z.string().default('').optional(),
  prerequisiteSubjects: z.string().default('').optional(),
});

const SubjectModal = ({ modalProps, subject, onClose }: SubjectModalProps) => {
  const { createSubject } = useSubjectStore();

  const { mode, onSubmit } = modalProps || {
    mode: 'create',
    onSubmit: async (data: z.infer<typeof FormSchema>) => {
      try {
        await createSubject(data);
        form.reset();
        toast({
          title: 'Thêm môn học thành công',
          description: 'Môn học đã được thêm vào hệ thống',
          variant: 'success',
          duration: 2000,
        });
        onClose?.();
      } catch (error) {
        toast({
          title: 'Thêm môn học thất bại',
          description: 'Vui lòng thử lại sau',
          variant: 'destructive',
          duration: 2000,
        });
      }
    },
  };

  const title = {
    read: 'Thông tin môn học',
    create: 'Thêm môn học mới',
    edit: 'Sửa thông tin môn học',
  }[mode];

  const form = useForm<z.infer<typeof FormSchema>>({
    mode: 'onTouched',
    resolver: zodResolver(FormSchema),
    defaultValues: mode === 'create' ? undefined : subject,
  });

  return (
    <DialogContent>
      <DialogHeader>
        <DialogTitle>{title}</DialogTitle>
        {mode !== 'read' && (
          <DialogDescription>Thay đổi thông tin môn học tại đây. Nhấn Lưu để cập nhật.</DialogDescription>
        )}
      </DialogHeader>

      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)}>
          <div className="grid gap-4 py-4">
            <FormField
              control={form.control}
              name="name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Tên môn học</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="code"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Mã môn</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="numberOfCredits"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Số tín chỉ</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="regularCoefficient"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Hệ số thường xuyên</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="midTermCoefficient"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Hệ số giữa kì</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="finalCoefficient"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Hệ số cuối kì</FormLabel>
                  <FormControl>
                    <Input {...field} type="number" />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="description"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Mô tả</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="prerequisiteSubjects"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Môn tiên quyết</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
          </div>
          {mode !== 'read' && (
            <DialogFooter>
              <Button type="submit" className="mt-2">
                {form.formState.isSubmitting ? 'Đang lưu...' : 'Lưu thay đổi'}
              </Button>
            </DialogFooter>
          )}
        </form>
      </Form>
    </DialogContent>
  );
};

export default SubjectModal;
