package study.rtaskV2.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
//데이터가 변경이 안되는 조회의 경우 트랜잭션 readOnly=true 속성을 주면
//em.flush();가 일어나지 않아 약간의 성능 향상에 도움이 된다.
@Transactional(readOnly = true)
@Rollback(value = false)
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void 회원_등록() {
        //given
        Member member = new Member("m1", new Address("incheon", "gill", "code"));
        em.persist(member);
        
        em.flush();
        em.clear();
        
        //when
        Member findMember = em.find(Member.class, member.getId());

        //given
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getCreatedDate()).isEqualTo(member.getCreatedDate());
        assertThat(findMember.getAddress().getCity()).isEqualTo(member.getAddress().getCity());
    }
}
