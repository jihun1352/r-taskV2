package study.rtaskV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.rtaskV2.domain.Notice;
import study.rtaskV2.dto.NoticeRequestDto;
import study.rtaskV2.dto.NoticeResponseDto;
import study.rtaskV2.repository.NoticeRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public NoticeResponseDto save(NoticeRequestDto noticeRequestDto) {
        Notice notice = noticeRepository.save(noticeRequestDto.toEntity());
        return new NoticeResponseDto(notice);
    }
}
