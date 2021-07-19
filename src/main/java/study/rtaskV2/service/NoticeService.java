package study.rtaskV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.rtaskV2.domain.Notice;
import study.rtaskV2.dto.NoticeRequestDto;
import study.rtaskV2.dto.NoticeResponseDto;
import study.rtaskV2.repository.NoticeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    //등록
    @Transactional
    public NoticeResponseDto save(NoticeRequestDto noticeRequestDto) {
        Notice notice = noticeRepository.save(noticeRequestDto.toEntity());
        return new NoticeResponseDto(notice);
    }

    //전체 조회
    public Page<NoticeResponseDto> findAll(Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAll(pageable);
        return notices.map(NoticeResponseDto::new);
    }

}
