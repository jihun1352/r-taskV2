package study.rtaskV2.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.rtaskV2.domain.Address;
import study.rtaskV2.domain.Member;

import java.time.LocalDateTime;

//회원가입 후 응답 데이터 dto
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private String name;
    private Address address;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public MemberResponseDto(Member member) {
        this.name = member.getName();
        this.address = member.getAddress();
        this.createdDate = member.getCreatedDate();
        this.lastModifiedDate = member.getLastModifiedDate();
    }
}
