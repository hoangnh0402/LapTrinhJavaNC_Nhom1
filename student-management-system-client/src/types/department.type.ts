import { Class } from '@/types/class.type';

export interface Department {
  id: string;
  name: string;
  code: string;
  dean?: string;
  description?: string;
  phoneNumber?: string;
  email?: string;
  location?: string;
  createdAt?: string;

  classes?: Class[];
}
