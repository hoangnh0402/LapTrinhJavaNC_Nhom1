export interface DocumentFile {
  name: string;
  path: string;
  type: string;
}

export interface Document {
  id: string;
  subjectId: string;
  name: string;
  type: string;
  description?: string;
  files: DocumentFile[];
  size?: number;
  createdBy?: string;
  createdDate?: string;
  path: string;
}
