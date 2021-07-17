package study.rtaskV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.rtaskV2.domain.Member;
import study.rtaskV2.dto.MemberRequestDto;
import study.rtaskV2.dto.MemberResponseDto;
import study.rtaskV2.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }

    //회원 전체 목록
    public List<MemberResponseDto> findAll() {
        //전체 회원 목록 조회
        List<Member> memberList = memberRepository.findAll();
        //조회한 회원 목록을 외부에 노출할 dto인 MemberResponseDto로 변환
        return memberList.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    //회원등록
    @Transactional
    public Member join(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.save(memberRequestDto.toEntity());
        return member;
    }

    //수정
    @Transactional
    public Member update(Long id, MemberRequestDto memberRequestDto) {
//        Optional<Member> findMember = memberRepository.findById(id);
        Member findMember = memberRepository.findById(id).get();
        findMember.update(memberRequestDto.getName(), memberRequestDto.getAddress());

        return findMember;
    }
}
