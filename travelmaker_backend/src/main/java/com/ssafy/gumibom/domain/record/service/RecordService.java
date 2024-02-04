package com.ssafy.gumibom.domain.record.service;

import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.pamphlet.repository.PersonalPamphletRepository;
import com.ssafy.gumibom.domain.pamphlet.service.PersonalPamphletService;
import com.ssafy.gumibom.domain.record.dto.request.SavePersonalRecordRequestDto;
import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
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

    private final RecordRepository recordRepository;
    private final PersonalPamphletRepository pPamphletRepository;

    @Autowired
    private S3Uploader s3Uploader;

    // 개인 팜플렛에 개인 기록 저장
    @Transactional
    public Long makePersonalRecord(MultipartFile image, MultipartFile video, SavePersonalRecordRequestDto dto) throws IOException {

        PersonalPamphlet pPamphlet = pPamphletRepository.findByPamphletId(dto.getPamphletId());
        String title = dto.getTitle();
        String imgUrl = "";
        String videoUrl = "";
        String text = dto.getText();

        if(image!=null) imgUrl = uploadImage(image);
        if(video!=null) videoUrl = uploadVideo(video);

        PersonalRecord pRecord = PersonalRecord.createPersonalRecord(title, imgUrl, videoUrl, text, pPamphlet);
        recordRepository.save(pRecord);

        return pRecord.getId();
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
