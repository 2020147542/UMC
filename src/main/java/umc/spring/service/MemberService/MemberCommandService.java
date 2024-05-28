package umc.spring.service.MemberService;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.api.request.MemberRequest;

public interface MemberCommandService {
    Member joinMember(MemberRequest.JoinDto joinRequest);
    boolean isExistFoodCategory(Long categoryId);
}
