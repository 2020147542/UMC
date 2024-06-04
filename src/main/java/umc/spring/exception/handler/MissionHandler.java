package umc.spring.exception.handler;

import umc.spring.api.response.common.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
