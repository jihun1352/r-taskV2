package study.rtaskV2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.rtaskV2.domain.Address;
import study.rtaskV2.domain.Member;
import study.rtaskV2.dto.MemberRequestDto;
import study.rtaskV2.dto.MemberResponseDto;
import study.rtaskV2.dto.MemberResultDto;
import study.rtaskV2.repository.MemberRepository;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @PostMapping("/member/join")
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
        Member save = memberRepository.save(request.toEntity());
        //저장된 member엔티티에서 필요한 정보만 출력하는 응답 dto로 리턴
        return new MemberResponseDto(save);
    }

    @GetMapping("/members")
    public MemberResultDto memberList() {
        //전체 회원 목록 조회
        List<Member> memberList = memberRepository.findAll();
        //조회한 회원 목록을 외부에 노출할 dto인 MemberResponseDto로 변환
        List<MemberResponseDto> memberDto = memberList.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
        //컬렉션 형태로 담아서 외부에 노출
        return new MemberResultDto(memberDto.size(), memberDto);
    }

    //샘플데이터 등록
    //초기에 실행
    @PostConstruct
    public void init() {
        for (int i = 0; i < 20; i++) {
            memberRepository.save(new Member("member"+i,
                    new Address("seoul"+i, "street"+i, "000-"+i)));
        }
    }
}
