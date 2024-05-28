package umc.spring.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequest {

    @Getter
    public static class AddDto{

        @NotBlank
        String name;

        @Size(min = 5, max = 12)
        String address;

        Float score;
    }
}
