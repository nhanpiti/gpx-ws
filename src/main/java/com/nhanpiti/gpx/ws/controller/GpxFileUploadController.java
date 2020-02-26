package com.nhanpiti.gpx.ws.controller;

import com.nhanpiti.gpx.ws.exception.StorageFileNotFoundException;
import com.nhanpiti.gpx.ws.service.FileSystemStorageService;
import com.nhanpiti.gpx.ws.service.GpxService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author nhanpiti
 */

@Controller
@RequestMapping("/gpx/upload")
public class GpxFileUploadController {

    private final FileSystemStorageService fileSystemStorageService;

    private final GpxService service;

    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public GpxFileUploadController(FileSystemStorageService fileSystemStorageService, GpxService service, JobLauncher jobLauncher, Job job) {
        this.fileSystemStorageService = fileSystemStorageService;
        this.service = service;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }


    @GetMapping
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", fileSystemStorageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(GpxFileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {

        fileSystemStorageService.store(file);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        if(fileSystemStorageService.getPathFile(file) != null) {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("pathFile", fileSystemStorageService.getPathFile(file))
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        }
        return "redirect:/gpx/upload";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
