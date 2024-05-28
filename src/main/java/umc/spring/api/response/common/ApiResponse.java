package umc.spring.api.response.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.api.response.common.code.BaseCode;
import umc.spring.api.response.common.code.status.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    // http 상태코드 외에, 우리가 자체적으로 응답설명을 위해 부여한 코드
    private final String code;
    private final String message;
    // null이 아닌 경우에만 JSON에 포함되도록 합니다.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    // 실패한 경우 응답 생성
    // <T>: 메서드가 여러 타입의 매개변수를 받을 수 있도록 합니다.
    // ApiResponse<T>: ApiResponse 타입의 객체를 반환
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){

        // ApiResponse 클래스의 생성자를 호출하여 새로운 ApiResponse 객체를 생성합니다.
        return new ApiResponse<>(false, code, message, data);
    }

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode baseCode, T result){
        return new ApiResponse<>(true, baseCode.getReasonHttpStatus().getCode(), baseCode.getReasonHttpStatus().getMessage(), result);
    }
}
