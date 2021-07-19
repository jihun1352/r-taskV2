package study.rtaskV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import study.rtaskV2.domain.Address;
import study.rtaskV2.domain.Member;
import study.rtaskV2.domain.Notice;
import study.rtaskV2.dto.MemberRequestDto;
import study.rtaskV2.dto.MemberResponseDto;
import study.rtaskV2.dto.MemberResultDto;
import study.rtaskV2.dto.NoticeRequestDto;
import study.rtaskV2.repository.MemberRepository;
import study.rtaskV2.repository.NoticeRepository;
import study.rtaskV2.service.MemberService;
import study.rtaskV2.service.NoticeService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final NoticeService noticeService;
    private final NoticeRepository noticeRepository;

    @PostMapping("/member")
    //@Valid로 MemberRequestDto.java에 지정한 필수값 확인
    public MemberResponseDto join(@RequestBody @Valid MemberRequestDto request) {
        //postMan 파라미터 테스트 body에 입력
//        {
//            "name": "member1",
//                "address": {
//            "city": "seoul",
//                    "street": "street",
//                    "zipCode": "111"
//            }
//        }
        //요청받은 값을 Member 엔티티로 변환해서 저장
        Member member = memberService.join(request);
        //저장된 member엔티티에서 필요한 정보만 출력하는 응답 dto로 리턴
        return new MemberResponseDto(member);
    }

    @GetMapping("/members")
    public MemberResultDto memberList() {
        List<MemberResponseDto> result = memberService.findAll();
        //컬렉션 형태로 담아서 외부에 노출
        return new MemberResultDto(result.size(), result);
    }

    //회원정보 수정
    @PutMapping("/member/{id}")
    public MemberResponseDto update(@PathVariable("id") Long id, @RequestBody @Valid MemberRequestDto request) {
        Member result = memberService.update(id, request);
        return new MemberResponseDto(result);
    }

    //샘플데이터 등록
    //초기에 실행
    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            memberRepository.save(new Member("member"+i,
                    new Address("seoul"+i, "street"+i, "000-"+i)));
        }
        Member member = memberRepository.save(new Member("회원",
                new Address("seoul", "street", "000-")));

        for (int i = 0; i < 10; i++) {
            noticeService.save(NoticeRequestDto.builder()
                    .member(member)
                    .title("제목"+ i)
                    .content("내용" + i)
                    .build());
        }

    }
}
