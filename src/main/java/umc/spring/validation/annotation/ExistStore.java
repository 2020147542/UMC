package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.StoresExistValidator;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션 만들때 사용
@Constraint(validatedBy = StoresExistValidator.class) // CategoriesExistValidator라는 클래스를 통해 @ExistCategories가 붙은 대상을 검증
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) // 어노테이션의 적용범위 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명주기
public @interface ExistStore {

    String message() default "해당하는 가게가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
