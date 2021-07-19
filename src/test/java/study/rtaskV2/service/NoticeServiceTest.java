package study.rtaskV2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import study.rtaskV2.domain.Member;
import study.rtaskV2.dto.MemberRequestDto;
import study.rtaskV2.dto.NoticeRequestDto;
import study.rtaskV2.dto.NoticeResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class NoticeServiceTest {

    @Autowired
    NoticeService noticeService;
    @Autowired
    MemberService memberService;

    @Test
    public void 공지사항_등록() {
        //given
        MemberRequestDto memberDto1 = MemberRequestDto.builder()
                .name("회원1")
                .build();
        MemberRequestDto memberDto2 = MemberRequestDto.builder()
                .name("회원2")
                .build();
        Member member1 = memberService.join(memberDto1);
        Member member2 = memberService.join(memberDto2);

        NoticeRequestDto noticeDto1 = NoticeRequestDto.builder()
                .member(member1)
                .title("제목입니다.")
                .content("내용")
                .build();
        NoticeRequestDto noticeDto2 = NoticeRequestDto.builder()
                .member(member1)
                .title("제목입니다.22")
                .content("내용22")
                .build();

        //when
        NoticeResponseDto result = noticeService.save(noticeDto1);
        NoticeResponseDto result2 = noticeService.save(noticeDto2);

        //then
        assertThat(result.getMember()).isEqualTo(member1);
        assertThat(result.getTitle()).isEqualTo(noticeDto1.getTitle());
        assertThat(result.getContent()).isEqualTo(noticeDto1.getContent());
//        assertThat(result.getMember().getNotices()).isEqualTo(member1.getNotices());

        System.out.println("member1.getNotices() = " + member1.getNotices());
        //System.out.println("result.getMember().getNotices() = " + result.getMember().getNotices());

        //System.out.println("result2.getMember().getNotices() = " + result2.getMember().getNotices());
        System.out.println("member2.getNotices() = " + member2.getNotices());
    }

    @Test
    public void 공지_전체_조회() {
        //given
        MemberRequestDto memberDto1 = MemberRequestDto.builder()
                .name("회원1")
                .build();
        MemberRequestDto memberDto2 = MemberRequestDto.builder()
                .name("회원2")
                .build();
        Member member1 = memberService.join(memberDto1);
        Member member2 = memberService.join(memberDto2);

        NoticeRequestDto noticeDto1 = NoticeRequestDto.builder()
                .member(member1)
                .title("제목입니다.")
                .content("내용")
                .build();
        NoticeRequestDto noticeDto2 = NoticeRequestDto.builder()
                .member(member1)
                .title("제목입니다.22")
                .content("내용22")
                .build();

        NoticeResponseDto result = noticeService.save(noticeDto1);
        NoticeResponseDto result2 = noticeService.save(noticeDto2);

        //when
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<NoticeResponseDto> notices = noticeService.findAll(pageRequest);

        //then
        List<NoticeResponseDto> content = notices.getContent();
        assertThat(notices.getTotalElements()).isEqualTo(2);
        assertThat(content.size()).isEqualTo(2);
    }
}