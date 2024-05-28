package umc.spring.service.StoreService;

import umc.spring.api.request.MemberRequest;
import umc.spring.api.request.StoreRequest;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public interface StoreCommandService {
    Store addStore(StoreRequest.AddDto addRequest, Long regionId);
}
