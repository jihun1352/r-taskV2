package study.rtaskV2.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.rtaskV2.domain.Address;
import study.rtaskV2.domain.Member;

import javax.validation.constraints.NotEmpty;

//회원가입 요청 dto
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDto {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;
    private Address address;

    //엔티티로 변환
    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .address(this.address)
                .build();
    }

    public MemberRequestDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
