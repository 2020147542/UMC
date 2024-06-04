package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.Review;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.repository.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    // get 요청에 대한 비즈니스 로직 처리
    @Override
    public Optional<Member> findMember(Long id){
        return memberRepository.findById(id);
    }

    @Override
    public boolean isExistMission(Long missionId){
        return missionRepository.existsById(missionId);
    }

    @Override
    public boolean isExistFoodCategory(Long categoryId){
        return foodCategoryRepository.existsById(categoryId);
    }

    @Override
    public Page<Review> getMemberReviews(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        return reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
    }

    @Override
    public Page<MemberMission> getMemberProgressMission(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page, 10));
    }

    @Override
    public Page<MemberMission> getMemberCompleteMission(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        return memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.COMPLETE, PageRequest.of(page, 10));
    }
}
