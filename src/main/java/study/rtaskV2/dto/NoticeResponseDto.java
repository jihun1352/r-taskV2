package study.rtaskV2.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.rtaskV2.domain.Member;
import study.rtaskV2.domain.Notice;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeResponseDto {
    private Member member;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public NoticeResponseDto(Notice notice) {
        this.member = notice.getMember();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdDate = notice.getCreatedDate();
        this.lastModifiedDate = notice.getLastModifiedDate();
    }
}
