import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from '@/components/ui/alert-dialog';
import { Button, buttonVariants } from '@/components/ui/button';

interface RegisterDialogProps {
  title: string;
  onConfirm: () => void;
}

export function RegisterDialog({ title, onConfirm }: RegisterDialogProps) {
  return (
    <AlertDialog>
      <AlertDialogTrigger asChild>
        <Button onSelect={(e) => e.preventDefault()}>{title}</Button>
      </AlertDialogTrigger>
      <AlertDialogContent>
        <AlertDialogHeader>
          <AlertDialogTitle>Bạn có chắc chắn không?</AlertDialogTitle>
          <AlertDialogDescription>Xác nhận đăng kí học phần.</AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          <AlertDialogCancel>Huỷ bỏ</AlertDialogCancel>
          <AlertDialogAction onClick={onConfirm}>Đăng kí</AlertDialogAction>
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  );
}
