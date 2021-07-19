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
    //출력할 내용만 보여줄 용도로 만든 dto를 호출
    //MemberResponseDto가 아닌 Member을 직접 사용할 경우 Notice와 서로 계속 참조하게 된다.
    private MemberResponseDto member;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public NoticeResponseDto(Notice notice) {
        this.member = new MemberResponseDto(notice.getMember());
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdDate = notice.getCreatedDate();
        this.lastModifiedDate = notice.getLastModifiedDate();
    }
}
