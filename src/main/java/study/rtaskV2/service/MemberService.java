package study.rtaskV2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.rtaskV2.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

}
