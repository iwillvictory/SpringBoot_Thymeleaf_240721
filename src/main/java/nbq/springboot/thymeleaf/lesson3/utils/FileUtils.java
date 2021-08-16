package nbq.springboot.thymeleaf.lesson3.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class FileUtils {
    public static void storeImgToFileSystem(String directory, MultipartFile multipartFile, String fileName) throws IOException {
        Path dir = Paths.get(directory);
        if(!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        try(InputStream inputStream = multipartFile.getInputStream()) {
            fileName = StringUtils.cleanPath(fileName);
            Path filePath = dir.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException("Could not save uploaded file : " + fileName);
        }
    }
}
