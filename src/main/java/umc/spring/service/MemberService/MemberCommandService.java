package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.api.request.MemberRequest;

public interface MemberCommandService {
    Member joinMember(MemberRequest.JoinDto joinRequest);
    void missionAdd(Long missionId, Long userId);
    void missionState(Long missionId, Long userId);
}
