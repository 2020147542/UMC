package umc.spring.exception.handler;

import umc.spring.api.response.common.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
