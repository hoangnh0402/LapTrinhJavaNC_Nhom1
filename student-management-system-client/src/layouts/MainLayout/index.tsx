import Logo from '@/assets/images/logo.png';
import { Button } from '@/components/ui/button';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuGroup,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarInset,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarProvider,
} from '@/components/ui/sidebar';

import { FaBook, FaUser } from 'react-icons/fa';
import { useUserStore } from '@/store/useUserStore';
import { Role } from '@/types/user.type';
import { ChevronDown, LogOut } from 'lucide-react';
import { FaUserGroup } from 'react-icons/fa6';
import { IoMdHome, IoMdKey } from 'react-icons/io';
import { MdClass } from 'react-icons/md';
import { RiTableView } from 'react-icons/ri';
import { SiGoogleclassroom } from 'react-icons/si';
import { Link, Outlet } from 'react-router-dom';

const items = [
  {
    title: 'Trang chủ',
    icon: IoMdHome,
    href: '/',
    tooltip: 'Trang chủ',
    roles: [Role.ADMIN, Role.TEACHER, Role.STUDENT],
  },
  {
    title: 'Quản lý sinh viên',
    icon: FaUser,
    href: '/admin/students',
    tooltip: 'Quản lý sinh viên',
    roles: [Role.ADMIN],
  },
  {
    title: 'Quản lý giảng viên',
    icon: FaUserGroup,
    href: '/admin/teachers',
    tooltip: 'Quản lý giảng viên',
    roles: [Role.ADMIN],
  },
  { title: 'Quản lý môn học', icon: MdClass, href: '/admin/subjects', tooltip: 'Quản lý môn học', roles: [Role.ADMIN] },
  {
    title: 'Quản lý lớp học',
    icon: SiGoogleclassroom,
    href: '/admin/classrooms',
    tooltip: 'Quản lý lớp học',
    roles: [Role.ADMIN],
  },
  {
    title: 'Học phần',
    icon: FaBook,
    href: '/lectures',
    tooltip: 'Học phần',
    roles: [Role.ADMIN, Role.TEACHER, Role.STUDENT],
  },
  {
    title: 'Đăng kí học phần',
    icon: IoMdKey,
    href: '/register',
    tooltip: 'Đăng kí học phần',
    roles: [Role.STUDENT],
  },
  {
    title: 'Xem thống kê điểm tích lũy',
    icon: RiTableView,
    href: '/summary',
    tooltip: 'Xem thống kê điểm tích lũy',
    roles: [Role.ADMIN, Role.TEACHER],
  },
  {
    title: 'Xem thông tin lớp học',
    icon: FaBook,
    href: '/classrooms',
    tooltip: 'Xem thông tin lớp học',
    roles: [Role.ADMIN, Role.TEACHER, Role.STUDENT],
  },
];

const MainLayout = () => {
  const { user, logout } = useUserStore();

  const visibleItems = items.filter((item) => item.roles.includes(user?.roleName as Role));

  return (
    <div className="flex min-h-screen flex-col">
      <header className="fixed top-0 z-50 w-full border-b bg-white backdrop-blur">
        <div className="flex h-16 items-center justify-between gap-4 px-4">
          <div className="flex items-center gap-4">
            <img src={Logo} alt="Logo" className="size-12" />
            <span className="text-lg leading-[60px] text-[#404040]">Cổng thông tin Sinh viên</span>
          </div>
          <div>
            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="ghost">
                  {user?.fullName}
                  <ChevronDown />
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent className="w-56">
                <DropdownMenuLabel>{user?.fullName}</DropdownMenuLabel>
                <DropdownMenuSeparator />
                <DropdownMenuGroup>
                  <DropdownMenuItem onClick={logout}>
                    <LogOut />
                    <span>Đăng xuất</span>
                  </DropdownMenuItem>
                </DropdownMenuGroup>
              </DropdownMenuContent>
            </DropdownMenu>
          </div>
        </div>
      </header>

      <div className="flex-1 pt-16">
        <SidebarProvider>
          <Sidebar collapsible="icon">
            <SidebarContent className="mt-16">
              <SidebarGroup>
                <SidebarGroupContent>
                  <SidebarMenu>
                    {visibleItems.map((item) => (
                      <SidebarMenuItem key={item.href}>
                        <SidebarMenuButton tooltip={item.tooltip} className="py-6">
                          {item.icon && <item.icon size={40} />}
                          <Link to={item.href}>
                            <span className="text-base">{item.title}</span>
                          </Link>
                        </SidebarMenuButton>
                      </SidebarMenuItem>
                    ))}
                  </SidebarMenu>
                </SidebarGroupContent>
              </SidebarGroup>
            </SidebarContent>
          </Sidebar>
          <SidebarInset className="bg-[#eeeeee] p-5">
            <Outlet />
          </SidebarInset>
        </SidebarProvider>
      </div>
    </div>
  );
};

export default MainLayout;
