package study.rtaskV2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.rtaskV2.domain.Member;
import study.rtaskV2.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

//    @Query(value = "select n from Notice n join fetch n.member m",
//    countQuery = "select count(n) from Notice n")
    @EntityGraph(attributePaths = {"member"})   //위의 @Query 사용과 결과 동일
    Page<Notice> findAll(Pageable pageable);
}
