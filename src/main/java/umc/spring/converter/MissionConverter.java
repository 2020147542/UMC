package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.api.request.MissionRequest;
import umc.spring.api.response.MissionResponse;
import umc.spring.domain.Mission;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MissionConverter {

    public static MissionResponse.AddResultDTO toAddResultDTO(Mission mission) {
        return MissionResponse.AddResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequest.MissionAddDto addRequest) {

        return Mission.builder()
                .name(addRequest.getName())
                .missionSpec(addRequest.getMissionSpec())
                .deadline(addRequest.getDeadline())
                .reward(addRequest.getReward())
                .build();
    }
}
