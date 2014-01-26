package traveler.utils;

import static org.springframework.util.DigestUtils.md5DigestAsHex;
import static org.springframework.util.FileCopyUtils.copy;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	public static void saveFile(MultipartFile file, String path) throws IOException {
		copy(file.getBytes(), new File(path));
	}
	
	public static String md5DigestOfFile(MultipartFile file) throws IOException {
		return md5DigestAsHex(file.getBytes());
	}
}
