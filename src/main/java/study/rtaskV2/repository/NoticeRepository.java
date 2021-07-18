package study.rtaskV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.rtaskV2.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
