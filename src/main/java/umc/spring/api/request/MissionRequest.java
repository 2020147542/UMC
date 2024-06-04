package umc.spring.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequest {

    @Getter
    public static class MissionAddDto {

        @NotBlank
        String name;

        @NotNull
        Integer reward;

        @NotNull
        LocalDate deadline;

        @NotNull
        String missionSpec;

    }
}
