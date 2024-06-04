package umc.spring.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequest {

    @Getter
    public static class ReviewAddDto {

        @NotBlank
        String title;

        @NotNull
        Float score;
    }
}
