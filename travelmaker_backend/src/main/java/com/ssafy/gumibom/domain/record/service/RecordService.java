package com.ssafy.gumibom.domain.record.service;

import com.ssafy.gumibom.domain.record.entity.Record;
import com.ssafy.gumibom.domain.record.repository.RecordRepository;
import com.ssafy.gumibom.global.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {

    @Autowired
    private final RecordRepository recordRepository;

    @Autowired
    private S3Uploader s3Uploader;

    @Transactional
    public Long uploadRecord(MultipartFile video, MultipartFile image, Record record) throws IOException {

        // 비디오 파일이 넘어온다면 -> S3에 업로드 후 record에 비디오 저장
        if(video != null) {
            String storedVideoFileName = s3Uploader.uploadFileToS3(video, "videos");
            record.setVideo(storedVideoFileName);
        }
        // 이미지 파일이 넘어온다면 -> S3에 업로드 후 record에 이미지 저장
        if(image != null) {
            String storedImageFileName = s3Uploader.uploadFileToS3(image, "images");
            record.setImage(storedImageFileName);
        }

        recordRepository.save(record);

        return record.getId();
    }

    // S3 이미지 업로드 테스트용 함수
    @Transactional
    public String uploadImage(MultipartFile image) throws IOException {
        String storedImageFileName = "";

        // 이미지 파일이 넘어온다면 -> S3에 업로드 후 record에 이미지 저장
        if(image != null) {
            storedImageFileName = s3Uploader.uploadFileToS3(image, "images");
        }

        return storedImageFileName;
    }

    // S3 영상 업로드 테스트용 함수
    @Transactional
    public String uploadVideo(MultipartFile video) throws IOException {
        String storedImageFileName = "";

        // 이미지 파일이 넘어온다면 -> S3에 업로드 후 record에 이미지 저장
        if(video != null) {
            storedImageFileName = s3Uploader.uploadFileToS3(video, "videos");
        }

        return storedImageFileName;
    }

}
