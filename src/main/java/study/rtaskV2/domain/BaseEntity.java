package study.rtaskV2.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseDateEntity {
    @CreatedBy
    @Column(updatable = false)  //등록자는 수정 안되도록 설정
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;
}
