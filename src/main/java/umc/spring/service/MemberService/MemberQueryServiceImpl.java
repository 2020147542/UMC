package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

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
}
