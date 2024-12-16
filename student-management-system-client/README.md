# Hệ thống quản lý sinh viên

## 📄 Mô tả

Hệ thống quản lý sinh viên là một ứng dụng web giúp quản lý thông tin sinh viên, hỗ trợ các chức năng như:

- Quản lý thông tin sinh viên
- Quản lý thông tin giảng viên
- Quản lý thông tin lớp học
- Quản lý thông tin môn học
- Quản lý điểm sinh viên
- ...

Ứng dụng được xây dựng với công nghệ hiện đại, giao diện thân thiện và dễ sử dụng.

## ✅ Yêu cầu

- Node.js
- npm (hoặc yarn)

## 🛠️ Công nghệ sử dụng

- [React](https://reactjs.org/) - Thư viện JavaScript dùng để xây dựng giao diện người dùng
- [Vite](https://vitejs.dev/) - Công cụ xây dựng ứng dụng web nhanh hơn
- [TypeScript](https://www.typescriptlang.org/) - Ngôn ngữ lập trình JavaScript với kiểu dữ liệu tĩnh
- [React Router](https://reactrouter.com/) - Thư viện React giúp điều hướng giữa các trang
- [TailwindCSS](https://tailwindcss.com/) - CSS Framework
- [shadcn/ui](https://ui.shadcn.com/) - Thư viện UI
- [Axios](https://axios-http.com/) - Thư viện HTTP Client
- [React Hook Form](https://react-hook-form.com/) - Thư viện React giúp quản lý form
- [Zod](https://zod.dev/) - Thư viện kiểm tra kiểu dữ liệu

## 📦 Cài đặt

1. Tải mã nguồn:

```bash
git clone https://github.com/hoangnh0402/LapTrinhJavaNC_Nhom1.git
cd student-management-system-client
```

2. Cài đặt các thư viện:

```bash
npm install
```

3. Tạo file `.env` từ file `.env.example` để sửa các biến môi trường:

```bash
cp .env.example .env
```

4. Chạy ứng dụng:

```bash
npm run dev
```

## 📁 Cấu trúc thư mục

```
student-management-system-client
├── public
└── src
    ├── assets            # Chứa tài nguyên như hình ảnh, font chữ
    ├── components        # Các component dùng chung
    ├── constants         # Các hằng số và cấu hình
    ├── hooks             # Custom hooks
    ├── layouts           # Các bố cục trang
    ├── lib               # Các hàm tiện tích
    ├── pages             # Các trang chính
    ├── routes            # Định nghĩa và quản lý route
    ├── services          # Quản lý các API
    ├── store             # Quản lý trạng thái (state) của ứng dụng
    ├── types             # Các kiểu dữ liệu TypeScript
    ├── App.tsx           # Component App chính
    └── index.tsx         # File khởi tạo ứng dụng
```
