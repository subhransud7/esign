package com.digio.esign.controller;

import com.digio.esign.entity.Document;
import com.digio.esign.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/document")
    public Resource uploadFile(@RequestPart("file") MultipartFile file,
                                   @RequestPart("metadata") Document document) throws IOException {
       // attachmentService.validateAttachment(file,document);
        if(document.getSigners().get(0).getIdentifier() == null){
            throw new IllegalStateException("Email/MobileNumber is required");
        }
        if(document.getFile_name() == null){
            throw new IllegalStateException("File Name is required");
        }
        attachmentService.setFileData(file,document);
        return attachmentService.sendDetails(document);

    }

    @GetMapping("/document/download/{fileId}")
    public ResponseEntity<Resource> downlaodFile(@PathVariable String fileId){
        return attachmentService.downlaodFile(fileId);
    }

    @GetMapping("/document/{fileId}")
    public ResponseEntity<Resource> showFile(@PathVariable String fileId){
        return attachmentService.getDetails(fileId);
    }
}
