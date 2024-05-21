package umc.spring.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TempResponse {


    // static: 해당 멤버가 인스턴스가 아닌 클래스에 속한다는 것을 의미
    // 인스턴스마다 별도로 존재하지 않고 클래스 수준에서 하나만 존재합니다.
    // TempResponse 클래스가 여러 DTO를 포함하는 컨테이너 역할
    // TempTestDTO가 TempResponse 내부에 정의됨으로써, 두 클래스가 논리적으로 관련이 있음
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempTestDTO{
        String testString;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO{
        Integer flag;
    }
}
