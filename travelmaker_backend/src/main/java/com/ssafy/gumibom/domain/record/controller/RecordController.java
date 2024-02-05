package com.ssafy.gumibom.domain.record.controller;

import com.ssafy.gumibom.domain.pamphlet.dto.request.MakePersonalPamphletRequestDto;
import com.ssafy.gumibom.domain.record.dto.request.DeletePersonalRecordRequestDto;
import com.ssafy.gumibom.domain.record.dto.request.SavePersonalRecordRequestDto;
import com.ssafy.gumibom.domain.record.dto.response.SavePersonalRecordResponseDto;
import com.ssafy.gumibom.domain.record.entity.PersonalRecord;
import com.ssafy.gumibom.domain.record.entity.Record;
import com.ssafy.gumibom.domain.record.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Path;

import java.io.IOException;

@Tag(name = "Record", description = "여행 기록 관련 api")
@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @Operation(summary = "개인 팜플렛에 여행 기록을 저장합니다.")
    @ResponseBody
    @PostMapping(value = "/personal-record", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public SavePersonalRecordResponseDto savePersonalRecord(
            @RequestPart(required = false) MultipartFile image,
            @RequestPart(required = false) MultipartFile video,
            @RequestPart SavePersonalRecordRequestDto sPRRDto) throws IOException {

        Long recordId = recordService.makePersonalRecord(image, video, sPRRDto);

        return new SavePersonalRecordResponseDto(recordId, true, "팜플렛에 기록을 저장했습니다.");
    }

    @Operation(summary = "개인 팜플렛에 저장된 여행 기록을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<?> removePersonalRecord(@RequestBody DeletePersonalRecordRequestDto dPRRDto) throws Exception {

        recordService.removePersonalRecord(dPRRDto.getPamphletId(), dPRRDto.getRecordId());

        return ResponseEntity.ok("기록이 삭제되었습니다.");
    }


//    // S3 이미지 업로드 테스트용 API
//    @Operation(summary = "[테스트용] S3에 이미지를 업로드합니다. ")
//    @ResponseBody
//    @PostMapping(value = "/test/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String uploadImg(HttpServletRequest request, @RequestParam(value = "image") MultipartFile image) throws IOException {
//        System.out.println(image);
//        System.out.println("=================");
//
//        return recordService.uploadImage(image);
//    }
//
//    // S3 비디오 업로드 테스트용 API
//    @Operation(summary = "[테스트용] S3에 비디오를 업로드합니다. ")
//    @ResponseBody
//    @PostMapping(value = "/test/upload/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public String uploadVideo(HttpServletRequest request, @RequestParam(value = "video") MultipartFile video) throws IOException {
//        System.out.println(video);
//        System.out.println("=================");
//
//        return recordService.uploadVideo(video);
//    }

}
