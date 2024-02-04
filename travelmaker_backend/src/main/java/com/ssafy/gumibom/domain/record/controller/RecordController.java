package com.ssafy.gumibom.domain.record.controller;

import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
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
    public Long savePersonalRecord(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "video", required = false) MultipartFile video,
            @RequestBody PersonalRecord pRecord) throws IOException {
            System.out.println(image);
            System.out.println(pRecord);
            System.out.println("=================");

            // 이미지가 들어왔다면, S3에 이미지 업로드 후 record에 imageUrl 저장
            if(!image.isEmpty()) {
                pRecord.setImage(recordService.uploadImage(image));
            }
            // 비디오가 들어왔다면, S3에 비디오 업로드 후 record에 videoUrl 저장
            if(!video.isEmpty()) {
                pRecord.setVideo(recordService.uploadVideo(video));
            }

        return recordService.makeRecord(null, image, pRecord);
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

}
