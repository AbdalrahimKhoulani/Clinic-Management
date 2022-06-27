package ClinicManagement.controller;


import ClinicManagement.entity.Attachment;
import ClinicManagement.entity.Doctor;
import ClinicManagement.entity.Patient;
import ClinicManagement.entity.Session;
import ClinicManagement.service.AttachmentService;
import ClinicManagement.service.PatientService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ctr {

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    private PatientService patientService;

    @PutMapping("/t")
    public String t(@RequestBody String s)
    {
        System.out.println(s);
        return s;
    }

    @GetMapping("/do/{id}")
    public Patient patient(@PathVariable("id")int id){
        return patientService.getPatientById(id,true);
    }

    @PostMapping("/test")
    public String Do(@RequestBody JSONObject jsonObject)
    {
        System.out.println(jsonObject.get("id"));
        return "eyad";
    }

    @GetMapping("/makePdf")
    public void d() throws IOException {

        File file = new File("E:\\testingFiles.pdf");
        byte[] bytes = new byte[(int)file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(bytes);
        Attachment attachment = new Attachment();
        Session session = new Session();
        session.setSessionId(1);
        attachment.setExtension("pdf");
        attachment.setName("eyad");
        attachment.setBytesFile(bytes);
        attachment.setSession(session);
        attachmentService.addAttachment(attachment);
    }
    @GetMapping("/writePdf")
    public void pdf() throws IOException {

        System.out.println(System.getProperty("user.dir"));

        Attachment attachment = attachmentService.getAttachmentById(6,true);
        byte [] a = attachment.getBytesFile() ;
        File file = new File("E:\\ newPdf.pdf");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(a);
        outputStream.flush();
        outputStream.close();

    }


}
