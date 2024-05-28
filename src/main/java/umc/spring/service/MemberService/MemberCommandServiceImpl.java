package umc.spring.service.MemberService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.exception.handler.FoodCategoryHandler;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.api.request.MemberRequest;
import umc.spring.api.response.common.code.status.ErrorStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    // 나머지 요청에 대한 비즈니스 로직 처리
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

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
    public boolean isExistFoodCategory(Long categoryId){
        return foodCategoryRepository.existsById(categoryId);
    }
}
