package study.rtaskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.rtaskV2.domain.Member;

import java.util.Optional;

//구현체가 SingleJpaRepository인 JpaRepository 인터페이스 상속
//스프링 데이터 JPA
public interface MemberRepository extends JpaRepository<Member, Long> {
    //이름으로 회원 검색
    Optional<Member> findByName(String name);
}
