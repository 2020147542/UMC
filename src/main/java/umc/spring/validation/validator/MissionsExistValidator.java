package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistStore;

@Component
@RequiredArgsConstructor
public class MissionsExistValidator implements ConstraintValidator<ExistMission, Long> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        boolean isValid = memberQueryService.isExistMission(value);

        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
