package com.ssafy.gumibom.domain.record.controller;

import com.ssafy.gumibom.domain.record.entity.Record;
import com.ssafy.gumibom.domain.record.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @ResponseBody
    @PostMapping(value = "/record/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long saveRecord(@RequestPart(required = false) MultipartFile image, Record record) throws IOException {
        System.out.println(image);
        System.out.println(record);
        System.out.println("=================");

        return recordService.uploadRecord(null, image, record);
    }


    // S3 이미지 업로드 테스트용 API
    @ResponseBody
    @PostMapping(value = "/test/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadImg(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image) throws IOException {
        System.out.println(image);
        System.out.println("=================");

        return recordService.uploadImage(image);
    }

    // S3 비디오 업로드 테스트용 API
    @ResponseBody
    @PostMapping(value = "/test/upload/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadVideo(HttpServletRequest request, @RequestParam(value = "video") MultipartFile video) throws IOException {
        System.out.println(video);
        System.out.println("=================");

        return recordService.uploadVideo(video);
    }

    @GetMapping(value = "/test/deploy")
    public String testDeploy() {
        return "deployTest";
    }

    public void test(){

    }

}
