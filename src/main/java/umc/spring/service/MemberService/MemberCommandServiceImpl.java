package umc.spring.service.MemberService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.exception.handler.FoodCategoryHandler;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.MissionHandler;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.api.request.MemberRequest;
import umc.spring.api.response.common.code.status.ErrorStatus;
import umc.spring.repository.MissionRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    // 나머지 요청에 대한 비즈니스 로직 처리
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional // 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    public Member joinMember(MemberRequest.JoinDto joinRequest){

        Member newMember = MemberConverter.toMember(joinRequest);
        List<FoodCategory> foodCategoryList = joinRequest.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(
                            () -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                })
                .toList();
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach(memberPrefer -> { memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public void missionAdd(Long missionId, Long userId){

        Optional<MemberMission> memberMission = memberMissionRepository.findByMissionIdAndMemberId(missionId, userId);
        if(memberMission.isEmpty()){
            Member member = memberRepository.findById(userId).orElseThrow(
                    () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
            );

            Mission mission = missionRepository.findById(missionId).orElseThrow(
                    () -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND)
            );

            memberMissionRepository.save(MemberMission.builder()
                    .mission(mission)
                    .member(member)
                    .status(MissionStatus.CHALLENGING)
                    .build());
        }
    }

    @Override
    @Transactional
    public void missionState(Long missionId, Long userId){

        MemberMission memberMission = memberMissionRepository.findByMissionIdAndMemberId(missionId, userId)
                .orElseThrow(NoSuchElementException::new);

        memberMission.setStatus(MissionStatus.COMPLETE);
    }
}
