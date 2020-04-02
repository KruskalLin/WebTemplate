package stg.template.template.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stg.template.template.dao.UserDao;
import stg.template.template.exception.BadRequestException;
import stg.template.template.exception.InternalException;
import stg.template.template.payloads.APIResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Set;


@RestController
@RequestMapping("/upload")
@Api(value = "File Upload API")
public class FileUploadController {

    @GetMapping("/files")
    @ApiOperation(value = "Upload file")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "file", paramType = "MultipartFile")})
    public APIResponse uploadFiles(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(), "static/");
        if ((!upload.exists() && !upload.mkdirs()))
            throw new InternalException("Create folder failed");
        String folderPath = upload.getAbsolutePath();
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty())
            throw new BadRequestException("File is empty");
        if (filename.contains("..")) {
            // This is a security check
            throw new BadRequestException(
                    "Cannot store file with relative path outside current directory " + filename);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(folderPath).resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return APIResponse.ofSuccess(filename);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalException("Upload failed");
        }
    }

}
