import { useDocumentStore } from '@/store/useDocumentStore';

const DownloadButton = ({ documentId }: { documentId: string }) => {
  const { getDocumentById } = useDocumentStore();
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const handleDownload = async () => {
    try {
      await getDocumentById(documentId);
      const updatedDocument = useDocumentStore.getState().document;
      if (!updatedDocument || !updatedDocument.path) {
        throw new Error('Không tìm thấy đường dẫn tải file');
      }
      // Tải file bằng fetch
      const response = await fetch(updatedDocument.path);
      if (!response.ok) {
        throw new Error('Tải file thất bại');
      }
      // Tạo blob từ dữ liệu file
      const blob = await response.blob();
      // Tạo URL blob và tải file
      const blobUrl = URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = blobUrl;
      link.download = updatedDocument.name || 'file-download';
      document.body.appendChild(link);
      link.click();
      // Dọn dẹp
      URL.revokeObjectURL(blobUrl);
      document.body.removeChild(link);
    } catch (error) {
      console.error('Lỗi tải file:', error);
      alert('Có lỗi xảy ra khi tải file. Vui lòng thử lại.');
    }
  };

  return (
    <button onClick={handleDownload} className="cursor-pointer text-blue-600 hover:text-blue-800">
      &#8681; Tải về
    </button>
  );
};

export default DownloadButton;
