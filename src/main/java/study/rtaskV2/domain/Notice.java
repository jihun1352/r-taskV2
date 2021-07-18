package study.rtaskV2.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"title", "content"})
public class Notice extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    //Member와 다대일 연관관계.
    //xToOne은 FetchType를 LAZY로 고정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String content;

    @Builder
    public Notice(Member member, String title, String content) {
        //공지사항 등록시에 연관관계 편의메서드 동작
        setMember(member);
        this.title = title;
        this.content = content;
    }

    //연관관계 편의 메서드
    //notice에 데이터 추가가 되면 Member 엔티티의 notices에 같이 추가
    public void setMember(Member member) {
        this.member = member;
        member.getNotices().add(this);
    }
}
