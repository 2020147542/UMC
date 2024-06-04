package umc.spring.api.request;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.domain.Store;

import java.time.LocalDate;

public class MissionRequest {

    @Getter
    public static class AddDto{

        @NotBlank
        String name;

        @NotBlank
        Integer reward;

        @NotNull
        LocalDate deadline;

        @NotNull
        String missionSpec;

    }
}
