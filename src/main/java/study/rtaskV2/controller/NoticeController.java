package study.rtaskV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import study.rtaskV2.dto.NoticeRequestDto;
import study.rtaskV2.dto.NoticeResponseDto;
import study.rtaskV2.service.NoticeService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/notice")
    public NoticeResponseDto save(@RequestBody @Valid NoticeRequestDto noticeRequestDto) {
        return noticeService.save(noticeRequestDto);
    }

    @GetMapping("/notices")
    public Page<NoticeResponseDto> findAll(@PageableDefault(page = 0, size = 3, sort = "createdDate",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return noticeService.findAll(pageable);
    }
}
