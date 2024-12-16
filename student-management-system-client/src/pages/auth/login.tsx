import { zodResolver } from '@hookform/resolvers/zod';

import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

import { z } from 'zod';

import { api } from '@/services/api.service';
import { useEffect, useState } from 'react';

import bgLogin from '@/assets/images/bg-login.jpg';
import logo from '@/assets/images/logo.png';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from '@/components/ui/card';
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form';
import { Input } from '@/components/ui/input';
import { Toaster } from '@/components/ui/toaster';
import { useToast } from '@/hooks/use-toast';
import { useUserStore } from '@/store/useUserStore';
import { Eye, EyeOff } from 'lucide-react'; // Import icons

const formSchema = z.object({
  username: z.string().min(1, {
    message: 'Mã sinh viên không được để trống',
  }),
  password: z.string().min(1, {
    message: 'Mật khẩu không được để trống',
  }),
});

const Login = () => {
  const { toast } = useToast();

  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false);

  const form = useForm<z.infer<typeof formSchema>>({
    mode: 'onTouched',
    resolver: zodResolver(formSchema),
    defaultValues: {
      username: '',
      password: '',
    },
  });

  const { user, loading, error, login } = useUserStore();

  async function onSubmit(data: z.infer<typeof formSchema>) {
    try {
      const { username, password } = data;
      await login(username, password);
      toast({
        title: 'Đăng nhập thành công',
        description: 'Chào mừng bạn đến với One HaUI',
        variant: 'default',
        duration: 1500,
        className: 'bg-green-600 text-white border-green-600',
      });
      setTimeout(() => {
        navigate('/');
      }, 1000);
    } catch (error) {
      toast({
        title: 'Đăng nhập thất bại',
        description: 'Kiểm tra lại mã sinh viên/mật khẩu',
        variant: 'destructive',
        duration: 1500,
      });
    }
  }

  return (
    <>
      <Toaster />
      <div className="flex h-screen w-screen items-center justify-center">
        <img className="fixed h-screen w-full object-cover" src={bgLogin} alt="" />

        <Card className="absolute px-6">
          <CardHeader className="flex items-center">
            <img className="mb-4 h-20 w-20" src={logo} alt="" />
            <CardTitle className="text-3xl">Đại học Công nghiệp Hà Nội</CardTitle>
            <CardDescription>One HaUI</CardDescription>
          </CardHeader>
          <CardContent className="">
            <Form {...form}>
              <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                <FormField
                  control={form.control}
                  name="username"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Mã sinh viên</FormLabel>
                      <FormControl>
                        <Input placeholder="" {...field} />
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />

                <FormField
                  control={form.control}
                  name="password"
                  render={({ field }) => (
                    <FormItem>
                      <FormLabel>Mật khẩu</FormLabel>
                      <FormControl>
                        <div className="relative">
                          <Input type={showPassword ? 'text' : 'password'} placeholder="" {...field} />
                          <button
                            type="button"
                            className="absolute right-3 top-1/2 -translate-y-1/2"
                            onClick={() => setShowPassword(!showPassword)}
                          >
                            {showPassword ? (
                              <EyeOff className="h-4 w-4 text-gray-500" />
                            ) : (
                              <Eye className="h-4 w-4 text-gray-500" />
                            )}
                          </button>
                        </div>
                      </FormControl>
                      <FormMessage />
                    </FormItem>
                  )}
                />
                <Button type="submit" disabled={form.formState.isSubmitting} className="w-full">
                  {form.formState.isSubmitting || loading ? 'Đang đăng nhập...' : 'Đăng nhập'}
                </Button>
              </form>
            </Form>
          </CardContent>

          <CardFooter className="justify-center">
            <CardDescription>Copyright 2024 © HaUI</CardDescription>
          </CardFooter>
        </Card>
      </div>
    </>
  );
};

export default Login;
