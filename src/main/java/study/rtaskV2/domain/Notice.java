package study.rtaskV2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notice extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    //Member와 다대일 연관관계.
    //xToOne은 FetchType를 LAZY로 고정
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String title;
    private String content;
}
