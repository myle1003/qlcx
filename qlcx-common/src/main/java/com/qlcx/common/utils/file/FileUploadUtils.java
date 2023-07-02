package com.qlcx.common.utils.file;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.qlcx.common.config.Global;
import com.qlcx.common.constant.Constants;
import com.qlcx.common.exception.file.FileNameLengthLimitExceededException;
import com.qlcx.common.exception.file.FileSizeLimitExceededException;
import com.qlcx.common.exception.file.InvalidExtensionException;
import com.qlcx.common.utils.DateUtils;
import com.qlcx.common.utils.StringUtils;
import com.qlcx.common.utils.security.Md5Utils;

/**
 * Công cụ tải tệp lên
 */
public class FileUploadUtils
{
    /**
     * Kích thước mặc định là 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * Độ dài tối đa mặc định của tên tệp là 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * Địa chỉ tải lên mặc định
     */
    private static String defaultBaseDir = Global.getProfile();

    private static int counter = 0;

    public static void setDefaultBaseDir(String defaultBaseDir)
    {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public static String getDefaultBaseDir()
    {
        return defaultBaseDir;
    }

    /**
     *Tải lên tệp với cấu hình mặc định
     *
     * @param Tệp tải lên tệp 
     * @return Tên tệp 
     * @throws Ngoại lệ 
     */
    public static final String upload(MultipartFile file) throws IOException
    {
        try
        {
            return upload(getDefaultBaseDir(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Tải lên theo đường dẫn tệp
     *
     * @param baseDir thư mục cơ sở ứng dụng tương đối
     * @param Tệp tải lên tệp 
     * @return Tên tệp 
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) throws IOException
    {
        try
        {
            return upload(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        }
        catch (Exception e)
        {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Tải lên tệp
     *
     * @param baseDir thư mục cơ sở ứng dụng tương đối
     * @param Tệp tải lên tệp 
     * @param cho phép Loại tệp tải lên tiện ích mở rộng
     * @return trả về tên của tệp đã tải lên thành công
     * @throws FileSizeLimitExceededException nếu vượt quá kích thước tối đa
     * @throws FileNameLengthLimitExceededException Tên tệp quá dài
     * @throws IOException, chẳng hạn như khi đọc và ghi tệp có lỗi
     * @throws Không hợp lệExtensionException Ngoại lệ xác minh tệp
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException,
            InvalidExtensionException
    {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength> FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension);

        String fileName = extractFilename(file);

        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;
    }

    /**
     * Tên tệp được mã hóa
     */
    public static final String extractFilename(MultipartFile file)
    {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = DateUtils.datePath() + "/" + encodingFilename(fileName) + "." + extension;
        return fileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException
    {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists())
        {
            desc.createNewFile();
        }
        return desc;
    }

    private static final String getPathFileName(String uploadDir, String fileName) throws IOException
    {
        int dirLastIndex = Global.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        String pathFileName = Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
        return pathFileName;
    }

    /**
     * Tên tệp được mã hóa
     */
    private static final String encodingFilename(String fileName)
    {
        fileName = fileName.replace("_", "");
        fileName = Md5Utils.hash(fileName + System.nanoTime() + counter++);
        return fileName;
    }

    /**
     * Kiểm tra kích thước tệp
     *
     * @param Tệp tải lên tệp 
     * @throws
     * @throws FileSizeLimitExceededException nếu vượt quá kích thước tối đa
     * @throws Không hợp lệ
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException
    {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE)
        {
            throw new FileSizeLimitExceededException(DEFAULT_MAX_SIZE / 1024 / 1024);
        }

        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension))
        {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.FLASH_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        fileName);
            }
            else if (allowedExtension == MimeTypeUtils.MEDIA_EXTENSION)
            {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        fileName);
            }
            else
            {
                throw new InvalidExtensionException(allowedExtension, extension, fileName);
            }
        }

    }

    /**
     * Xác định xem kiểu MIME có được phép Kiểu MIME không
    *
    * @param Phần mở rộng 
    * @param cho phép
    * @return
    */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension)
    {
        for (String str: allowedExtension)
        {
            if (str.equalsIgnoreCase(extension))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Lấy hậu tố của tên tệp
     *
     * @param tệp biểu mẫu tệp 
     * @return Tên hậu tố 
     */
    public static final String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}