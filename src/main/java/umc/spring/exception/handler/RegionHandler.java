package umc.spring.exception.handler;

import umc.spring.api.response.common.code.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
