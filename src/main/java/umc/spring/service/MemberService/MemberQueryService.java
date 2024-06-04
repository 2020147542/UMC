package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.Review;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    boolean isExistMission(Long missionId);
    boolean isExistFoodCategory(Long categoryId);
    Page<Review> getMemberReviews(Long memberId, Integer page);
    Page<MemberMission> getMemberProgressMission(Long memberId, Integer page);
    Page<MemberMission> getMemberCompleteMission(Long memberId, Integer page);
}
