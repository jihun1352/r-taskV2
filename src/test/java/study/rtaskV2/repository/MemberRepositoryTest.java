package study.rtaskV2.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.rtaskV2.domain.Address;
import study.rtaskV2.domain.Member;
import study.rtaskV2.dto.MemberRequestDto;
import study.rtaskV2.service.MemberService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    public void 회원_가입() {
        //given
        memberRepository.save(new Member("name1"));

        //when
        Member findMember = memberRepository.findByName("name1");

        //then
        assertThat(findMember.getName()).isEqualTo("name1");
    }

    @Test
    public void 회원_전체_조회() {
        //given
        memberSave("member1", "seoul", "street", "111");
        memberSave("member2", "seoul", "street", "111");
        memberSave("member3", "seoul", "street", "111");
        memberSave("member4", "seoul", "street", "111");
        
        //when
        List<Member> memberList = memberRepository.findAll();
        
        //then
        assertThat(memberList.size()).isEqualTo(4);
        for (Member member : memberList) {
            System.out.println("member = " + member);
        }
    }

    @Test
    @Transactional
    public void 회원_수정() {
        //given
        Member member1 = memberSave("member1", "seoul", "street", "000");
        memberRepository.save(member1);

        //when
        Member findMember = memberRepository.findById(member1.getId()).get();
        memberService.update(findMember.getId(), new MemberRequestDto("member2", findMember.getAddress()));

        em.flush();
        em.clear();

        //then
        assertThat(findMember.getName()).isEqualTo("member2");

    }

    //회원 등록 메서드
    public Member memberSave(String name, String city, String street, String zipCode) {
        Address address = new Address(city, street, zipCode);
        Member member = new Member(name, address);
        memberRepository.save(member);
        return member;
    }
}