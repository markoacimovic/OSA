package rs.ftn.osa.support;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

public class ImageSaveOpen {

    private static String filePath = "src\\main\\js\\public\\img";

    public static String saveImage(MultipartFile file) throws IOException {

        Path copyLocation = Paths.get(filePath + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        String putanja = "";

        String regex = Pattern.quote(System.getProperty("file.separator"));
        String[] split = copyLocation.toString().split(regex);
        putanja = "/" + split[4]+ "/"+split[5];

        return putanja;
    }}
