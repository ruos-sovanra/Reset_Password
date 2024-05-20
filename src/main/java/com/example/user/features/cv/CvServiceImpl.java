package com.example.user.features.cv;

import com.example.user.features.cv.dto.CvRespone;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CvServiceImpl implements CvService{
    @Value("${file.storage-dir1}")
    String fileStorageDir;

    private String education;
    private String project;
    private String awards;
    private String contact;
    private String skills;
    private String language;
    private String personal;
    private String email;
    private String phone;
    private String address;
    private String name;
    private String reference;

    private static final Set<String> SUPPORTED_IMAGE_TYPES = Set.of(
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    private String generateImageUrl(HttpServletRequest request, String filename) {
        return String.format("%s://%s:%d/images/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                filename);
    }

    public String readPdfContent(InputStream inputStream) {
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading PDF content", e);
        }
    }

    private String generateDownloadImageUrl(HttpServletRequest request, String filename) {
        return String.format("%s://%s:%d/api/v1/files/download/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                filename);
    }

    private String uploadFile(MultipartFile file) {
        String contentType = file.getContentType();
        if(!SUPPORTED_IMAGE_TYPES.contains(contentType)){
            throw new ResponseStatusException(
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    contentType + " not  allowed!! ");
        }
        try {

            Path fileStoragePath = Path.of(fileStorageDir);
            if (!Files.exists(fileStoragePath)) {
                Files.createDirectories(fileStoragePath);
            }
            String fileName = UUID.randomUUID() + "." +
                    Objects.requireNonNull(file.getOriginalFilename())
                            .split("\\.")[1];


            Files.copy(file.getInputStream(),
                    fileStoragePath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public CvRespone uploadSingleFile(MultipartFile file, HttpServletRequest request) {
        String filename = uploadFile(file);
        String fullImageUrl = generateImageUrl(request, filename);
        String content = null;

        // Check if the file is a PDF
        if ("application/pdf".equals(file.getContentType())||"application/msword".equals(file.getContentType())||"application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(file.getContentType())) {
            try {
                assert filename != null;
                // Read the content of the PDF
                if("application/msword".equals(file.getContentType())||"application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(file.getContentType())) {
                    content = readDocxContent(file.getInputStream());
                    String txtFileName1 = filename.replace(".docx", ".txt");
                    Path txtFilePath1 = Path.of(fileStorageDir).resolve(txtFileName1);
                    Files.writeString(txtFilePath1, content);
                }
                else if("application/pdf".equals(file.getContentType())){
                    content = readPdfContent(file.getInputStream());
                    String txtFileName = filename.replace(".pdf", ".txt");
                    Path txtFilePath = Path.of(fileStorageDir).resolve(txtFileName);
                    Files.writeString(txtFilePath, content);
                }


            } catch (IOException e) {
                throw new RuntimeException("Error occurred while reading PDF content", e);
            }
        }
        if (content == null) {
            content = ""; // Ensure content is not null for further processing
        }

        // Define section titles in the order of personal data

        Map<String, String> resumeSections = extractInformation(content);

        for (Map.Entry<String, String> entry : resumeSections.entrySet()) {
            String sectionTitle = entry.getKey();
            String sectionContent = entry.getValue();
            System.out.println(sectionTitle + ":");
            System.out.println(sectionContent);
            System.out.println();
        }
        personal = resumeSections.get("PERSONAL");
        education = resumeSections.get("EDUCATION");
        project = resumeSections.get("PROJECT");
        awards = resumeSections.get("AWARDS RECEIVED");
        contact = resumeSections.get("CONTACT");
        skills = resumeSections.get("SKILLS SUMMARY");
        language = resumeSections.get("LANGUAGE");
        address = resumeSections.get("ADDRESS");
        email = resumeSections.get("EMAIL");
        phone = resumeSections.get("PHONE");
        name = resumeSections.get("NAME");
        reference = resumeSections.get("REFERENCE");


        skills = resumeSections.get("SKILLS SUMMARY");
        language = resumeSections.get("LANGUAGE");
        return new CvRespone(
                generateDownloadImageUrl(request,filename),
                file.getContentType(),
                (float) file.getSize() / 1024, // in KB
                filename,
                fullImageUrl,
                content // Add the content to the response
        );
    }

    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] files) {
        return null;
    }

    public static Map<String, String> extractInformation(String content) {
        Map<String, String> resumeSections = new HashMap<>();

        // Define regex patterns for section titles
        String sectionRegex = "(?i)(PERSONAL|EDUCATION|PROJECT|AWARDS RECEIVED|CONTACT|SKILLS SUMMARY|LANGUAGE|ADDRESS|EMAIL|PHONE|NAME|REFERENCE)";
        Pattern pattern = Pattern.compile(sectionRegex);
        Matcher matcher = pattern.matcher(content);

        int previousMatchEnd = 0;
        while (matcher.find()) {
            // Extract section title and content based on match positions
            String sectionTitle = matcher.group().toUpperCase();
            int sectionStart = matcher.start();
            String sectionContent = content.substring(previousMatchEnd, sectionStart).trim();

            // Add the section to the map
            resumeSections.put(sectionTitle, sectionContent);

            previousMatchEnd = matcher.end();
        }

        // Add the last section (if any) after the last match
        if (previousMatchEnd < content.length()) {
            String lastSectionTitle = "REFERENCE"; // Assuming the last section is "REFERENCE"
            String lastSectionContent = content.substring(previousMatchEnd).trim();
            resumeSections.put(lastSectionTitle, lastSectionContent);
        }

        return resumeSections;
    }
    @Override
    public ResponseEntity<Resource> serveFile(String filename, HttpServletRequest request) {
        try {
            Path filePath = Path.of(fileStorageDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }else {
                throw new RuntimeException("Resources not found ! ");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error occurred while serving file", ex);
        }
    }

    @Override
    public void deleteFile(String filename) {

    }

    private String readDocxContent(InputStream inputStream) {
        try (XWPFDocument docx = new XWPFDocument(inputStream)) {
            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
            return extractor.getText();
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while reading DOCX content", e);
        }
    }

}
