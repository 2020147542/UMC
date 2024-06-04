package umc.spring.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequest {

    @Getter
    public static class StoreAddDto {

        @NotBlank
        String name;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 10, max = 200)
        String description;
    }
}
