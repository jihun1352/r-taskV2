package study.rtaskV2.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.rtaskV2.domain.Member;
import study.rtaskV2.domain.Notice;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeRequestDto {
    private Member member;
    private String title;
    private String content;

    public Notice toEntity() {
        return Notice.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public NoticeRequestDto(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
