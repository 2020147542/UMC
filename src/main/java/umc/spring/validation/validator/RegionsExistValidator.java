package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.domain.Region;
import umc.spring.service.MemberService.MemberCommandServiceImpl;
import umc.spring.service.RegionService.RegionQueryService;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistRegion;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegionsExistValidator implements ConstraintValidator<ExistRegion, Long> {
    // ExistCategories 어노테이션에 대한 로직을 담을 것이며 검증 대상이 List<Long>임을 명시

    private final RegionQueryService regionQueryService;

    @Override
    public void initialize(ExistRegion constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        // isValid함수에서 검증 대상인 List<Long> 의 값을 가진 카테고리가 모두 데이터베이스에 있는 지를 판단하고
        boolean isValid = regionQueryService.isExistRegion(value);

        // 없을 경우 false를 반환
        if (!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
