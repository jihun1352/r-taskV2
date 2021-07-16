package study.rtaskV2.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//Auditing 사용하기 위한 @EntityListeners 셋팅
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass   //상속받는 엔티티에 필드 추가
@Getter
public abstract class BaseDateEntity {
    @CreatedDate
    @Column(updatable = false)  //등록일은 수정 안되도록 설정
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
