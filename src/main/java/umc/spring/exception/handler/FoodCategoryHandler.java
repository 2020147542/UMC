package umc.spring.exception.handler;

import umc.spring.exception.GeneralException;
import umc.spring.api.response.common.code.BaseErrorCode;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
