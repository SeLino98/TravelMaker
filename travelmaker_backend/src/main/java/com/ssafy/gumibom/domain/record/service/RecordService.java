package com.ssafy.gumibom.domain.record.service;

import com.ssafy.gumibom.domain.pamphlet.entity.PersonalPamphlet;
import com.ssafy.gumibom.domain.pamphlet.repository.PersonalPamphletRepository;
import com.ssafy.gumibom.domain.pamphlet.service.PersonalPamphletService;
import com.ssafy.gumibom.domain.record.dto.request.SavePersonalRecordRequestDto;
import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
import com.ssafy.gumibom.domain.record.entity.Record;
import com.ssafy.gumibom.domain.record.repository.RecordRepository;
import com.ssafy.gumibom.global.common.Emoji;
import com.ssafy.gumibom.global.util.S3Service;
import com.ssafy.gumibom.global.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final PersonalPamphletRepository pPamphletRepository;

    private final S3Service s3Service;

    // 개인 팜플렛에 여행 기록 저장
    @Transactional
    public Long makePersonalRecord(MultipartFile image, MultipartFile video, SavePersonalRecordRequestDto dto) throws IOException {

        PersonalPamphlet pPamphlet = pPamphletRepository.findByPamphletId(dto.getPamphletId());
        String title = dto.getTitle();
        String imgUrl = "";
        String videoUrl = "";
        String text = dto.getText();
        Emoji emoji = dto.getEmoji();

        if(image!=null) imgUrl = s3Service.uploadS3(image, "images");
        if(video!=null) videoUrl = s3Service.uploadS3(video, "videos");

        PersonalRecord pRecord = PersonalRecord.createPersonalRecord(title, imgUrl, videoUrl, text, pPamphlet, emoji);
        recordRepository.save(pRecord);

        return pRecord.getId();
    }

    // 여행 기록 삭제
    @Transactional
    public void removePersonalRecord(Long pamphletId, Long recordId) throws Exception {

        PersonalPamphlet pPamphlet = pPamphletRepository.findByPamphletId(pamphletId);
        PersonalRecord pRecord = (PersonalRecord) recordRepository.findOne(recordId);

        String imgUrl = pRecord.getImgUrl();
        String videoUrl = pRecord.getVideoUrl();

        if(!Objects.equals(imgUrl, "")) s3Service.deleteS3(imgUrl);
        if(!Objects.equals(videoUrl, "")) s3Service.deleteS3(videoUrl);

        pPamphlet.removeRecord(pRecord);
        recordRepository.delete(pRecord);
    }


}
