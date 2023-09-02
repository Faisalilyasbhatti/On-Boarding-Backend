package com.evantagesoft.api.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.jcr.Node;
import javax.servlet.http.HttpServletRequest;

import com.evantagesoft.util.SystemConstant;
import com.evantagesoft.util.SystemUtil;
import com.evantagesoft.util.cms.ContentRepositoryProcessor;
import com.evantagesoft.util.cms.JcrRepository;
import org.slf4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.evantagesoft.entities.file.UploadFileResponse;
import com.evantagesoft.service.file.FileService;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.response.Response;
import com.google.common.net.HttpHeaders;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        Response response = new Response();
        Map<String, String> fileName = (Map<String, String>) fileService.storeFile(file);

        String orignal = fileName.get("orignalName");
        String created = fileName.get("createdName");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(created)
                .toUriString();

        UploadFileResponse uploadFileResponse = new UploadFileResponse(orignal, created, fileDownloadUri,
                file.getContentType(), file.getSize());

        response.setResponse(EVSResponse.SUCCESS);
        response.setData("fileData", uploadFileResponse);

        return response;
    }

    @GetMapping("/view/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public ResponseEntity<?> addImage(@RequestParam("file") MultipartFile file) {
        Response response = new Response();
        try {
            ContentRepositoryProcessor content = new ContentRepositoryProcessor();
            System.out.println(file.getContentType() + " \t " + file.getName() + " \t " + file.getOriginalFilename());
            String url = content.addFileNode(SystemConstant.TEMP_DOCUMENTS, file.getInputStream(),
                    SystemUtil.getFileName(file.getOriginalFilename()),
                    file.getContentType());
            response.setResponse(EVSResponse.SUCCESS);
            response.setData("fileID", url);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResponse(EVSResponse.SYSTEM_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ResponseEntity<?> getFile(@RequestParam("fileID") String fileID,
                                     @RequestParam("customerID") String customerID, HttpServletRequest request) {
        try {
            ContentRepositoryProcessor repositoryProcessor = new ContentRepositoryProcessor();
            Node fileNode = repositoryProcessor.getFileNode(customerID, fileID);
            InputStream ios = repositoryProcessor.getFileStream(fileNode);
            Resource resource = new InputStreamResource(ios);
            String contentType = repositoryProcessor.getMimeType(fileNode);
            String fileName = repositoryProcessor.getFileName(fileNode);
            if (contentType == null) {
                contentType = "image/jpeg";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("File not found", HttpStatus.OK);
    }

    @RequestMapping(value = "/move", method = RequestMethod.GET)
    public ResponseEntity<?> moveFile(@RequestParam("fileID") String fileID,
                                      @RequestParam("customerID") String customerID, HttpServletRequest request) {
        try {
            ContentRepositoryProcessor repositoryProcessor = new ContentRepositoryProcessor();
            repositoryProcessor.moveNode(customerID, fileID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("File not found", HttpStatus.OK);
    }
}
