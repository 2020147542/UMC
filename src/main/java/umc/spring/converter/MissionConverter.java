package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.spring.api.request.MissionRequest;
import umc.spring.api.response.MissionResponse;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDateTime;
import java.util.List;

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

    public static MissionResponse.MissionPreviewDTO toMissionPreviewDTO (Mission mission) {
        return MissionResponse.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MissionResponse.MissionPreviewDTO toMissionPreviewDTOFromMemberMission (MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return MissionResponse.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .deadline(mission.getDeadline())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MissionResponse.MissionListDTO toMissionListDTO(Page<Mission> missions) {

        List<MissionResponse.MissionPreviewDTO> missionPreviewDTOList = missions.stream()
                .map(MissionConverter::toMissionPreviewDTO).toList();

        return MissionResponse.MissionListDTO.builder()
                .missionList(missionPreviewDTOList)
                .isFirst(missions.isFirst())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .build();
    }

    public static MissionResponse.MissionListDTO toMissionListDTOFromMemberMission(Page<MemberMission> missions) {

        List<MissionResponse.MissionPreviewDTO> missionPreviewDTOList = missions.stream()
                .map(MissionConverter::toMissionPreviewDTOFromMemberMission).toList();

        return MissionResponse.MissionListDTO.builder()
                .missionList(missionPreviewDTOList)
                .isFirst(missions.isFirst())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .listSize(missionPreviewDTOList.size())
                .build();
    }
}
