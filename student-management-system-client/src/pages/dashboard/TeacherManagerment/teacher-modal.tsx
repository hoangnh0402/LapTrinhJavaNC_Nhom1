// @ts-nocheck
import { zodResolver } from '@hookform/resolvers/zod';
import { useState } from 'react';
import { useForm } from 'react-hook-form';
import { z } from 'zod';

import { Button } from '@/components/ui/button';
import DatePicker from '@/components/ui/date-picker';
import { DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { useTeacherStore } from '@/store/useTeacherStore';
import { Gender, User } from '@/types/user.type';
import { apiTempUpload } from '@/services/api.service';
import { toast } from '@/hooks/use-toast';

interface TeacherModalProps {
  modalProps?: {
    mode: 'read' | 'create' | 'edit';
    onSubmit: (data: z.infer<typeof FormSchema>) => void;
  };
  teacher?: User;
  onClose?: () => void;
}

export const FormSchema = z.object({
  username: z.string({ required_error: 'Mã giảng viên không được để trống' }),
  email: z.string({ required_error: 'Email không được để trống' }).email({ message: 'Email không hợp lệ' }),
  phoneNumber: z.string({ required_error: 'Số điện thoại không được để trống' }).refine((v) => v.length === 10, {
    message: 'Số điện thoại phải có 10 chữ số',
  }),
  fullName: z.string({ required_error: 'Họ tên không được để trống' }),
  gender: z.nativeEnum(Gender, { required_error: 'Giới tính không được để trống' }),
  birthday: z.string().nullable().optional(),
  avatar: z.instanceof(File).optional(),
});

export const handleFormData = async (data: z.infer<typeof FormSchema>) => {
  try {
    if (!data.avatar) return { ...data };
    let avatar = '';
    if (data.avatar instanceof File) {
      const formData = new FormData();
      formData.append('image', data.avatar);
      const response = await apiTempUpload.post('/upload', formData);
      avatar = response.data.url;
    }
    return { ...data, avatar };
  } catch (error) {
    return { ...data };
  }
};

const TeacherModal = ({ modalProps, teacher, onClose }: TeacherModalProps) => {
  const { createTeacher } = useTeacherStore();

  const [imageUrl, setImageUrl] = useState<string>(teacher?.avatar || 'https://placehold.co/140x100?text=404');

  const { mode, onSubmit } = modalProps || {
    mode: 'create',
    onSubmit: async (data: z.infer<typeof FormSchema>) => {
      try {
        const formData = await handleFormData(data);
        const { username, ...rest } = formData;
        const teacher = { ...rest, username, password: username };
        await createTeacher(teacher);
        form.reset();
        toast({
          title: 'Thêm giảng viên thành công',
          description: 'Giảng viên đã được thêm vào hệ thống',
          variant: 'success',
          duration: 2000,
        });
        onClose?.();
      } catch (error) {
        toast({
          title: 'Thêm giảng viên thất bại',
          description: 'Vui lòng thử lại sau',
          variant: 'destructive',
          duration: 2000,
        });
      }
    },
  };

  const title = {
    read: 'Thông tin giảng viên',
    create: 'Thêm giảng viên mới',
    edit: 'Sửa thông tin giảng viên',
  }[mode];

  const form = useForm<z.infer<typeof FormSchema>>({
    mode: 'onTouched',
    resolver: zodResolver(FormSchema),
    defaultValues:
      mode === 'create'
        ? undefined
        : {
            ...teacher,
            gender: teacher?.gender as Gender,
            avatar: undefined,
          },
  });

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>, onChange: (value: File) => void) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      onChange(file);
      reader.onload = () => {
        setImageUrl(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <DialogContent>
      <DialogHeader>
        <DialogTitle>{title}</DialogTitle>
        {mode !== 'read' && (
          <DialogDescription>Thay đổi thông tin giảng viên tại đây. Nhấn Lưu để cập nhật.</DialogDescription>
        )}
      </DialogHeader>

      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)}>
          <div className="grid gap-4 py-4">
            <FormField
              control={form.control}
              name="username"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Mã</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="fullName"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Họ tên</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="phoneNumber"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Số điện thoại</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email</FormLabel>
                  <FormControl>
                    <Input {...field} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="gender"
              render={({ field }) => (
                <FormItem className="flex items-center gap-2">
                  <FormLabel>Giới tính</FormLabel>
                  <FormControl>
                    <RadioGroup onValueChange={field.onChange} defaultValue={field.value} className="!mt-0 flex">
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value={Gender.MALE} />
                        </FormControl>
                        <FormLabel className="font-normal">Nam</FormLabel>
                      </FormItem>
                      <FormItem className="flex items-center space-x-3 space-y-0">
                        <FormControl>
                          <RadioGroupItem value={Gender.FEMALE} />
                        </FormControl>
                        <FormLabel className="font-normal">Nữ</FormLabel>
                      </FormItem>
                    </RadioGroup>
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="birthday"
              render={({ field }) => (
                <FormItem className="flex items-center gap-2">
                  <FormLabel>Ngày sinh</FormLabel>
                  <FormControl>
                    <DatePicker value={field.value} onChange={field.onChange} />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="avatar"
              render={({ field: { onChange, value, ...field } }) => (
                <FormItem className="flex items-center gap-2">
                  <FormLabel>Ảnh</FormLabel>
                  {mode !== 'read' && (
                    <FormControl>
                      <Input {...field} type="file" onChange={(e) => handleImageChange(e, onChange)} accept="image/*" />
                    </FormControl>
                  )}
                  <FormMessage />
                </FormItem>
              )}
            />{' '}
            {imageUrl && <img src={imageUrl} alt="Ảnh" className="h-[140px] w-[100px] object-cover" />}
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

export default TeacherModal;
