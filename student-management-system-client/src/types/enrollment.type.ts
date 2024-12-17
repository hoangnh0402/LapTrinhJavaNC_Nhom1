/* eslint-disable @typescript-eslint/no-explicit-any */
export interface Enrollment {
  id: string;
  userID: string;
  classroomId: string;
  firstRegularPoint?: number;
  secondRegularPoint?: number;
  midTermPoint?: number;
  finalPoint?: number;
  createdAt?: string;
  username?: string;
  fullName?: string;
  subjectName?: string;
  classroomCode?: string;
}

export interface UpdateScoreModalProps {
  enrollment: Enrollment;
  onSubmit: (updatedData: any) => void; // Giao diện chấp nhận dữ liệu cập nhật
  isOpen: boolean;
  onClose: () => void;
  classroomId: string;
}
