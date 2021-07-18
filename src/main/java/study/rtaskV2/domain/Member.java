package study.rtaskV2.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
@Getter
public class Member extends BaseDateEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    //값 타입 데이터 Address
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Notice> notices = new ArrayList<>();

    @Builder
    public Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Member(String name) {
        this.name = name;
    }

    //수정 메서드 생성
    public void update(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
