package study.rtaskV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.rtaskV2.dto.NoticeResponseDto;
import study.rtaskV2.service.NoticeService;

@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/notices")
    public Page<NoticeResponseDto> findAll(@PageableDefault(page = 0, size = 3, sort = "createdDate",
    direction = Sort.Direction.DESC) Pageable pageable) {

        return noticeService.findAll(pageable);
    }
}
