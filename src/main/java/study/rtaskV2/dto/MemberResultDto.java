package study.rtaskV2.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//회원 전체 목록 조회 dto
//컬렉션으로 출력하기 위해 제네릭 사용
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResultDto<T> {
    private long count;
    private T data;

    public MemberResultDto(long count, T data) {
        this.count = count;
        this.data = data;
    }
}
