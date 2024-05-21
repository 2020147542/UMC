package umc.spring.exception.handler;

import umc.spring.apiFormat.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        // 부모 클래스의 생성자 호출
        super(errorCode);
    }
}
