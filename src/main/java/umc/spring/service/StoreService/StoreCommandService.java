package umc.spring.service.StoreService;

import umc.spring.api.request.MissionRequest;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.request.StoreRequest;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.Store;

public interface StoreCommandService {
    Store addStore(StoreRequest.StoreAddDto addRequest, Long regionId);
    Review addReview(ReviewRequest.ReviewAddDto reviewRequest, Long userId, Long storeId);
    Mission addMission(MissionRequest.MissionAddDto missionRequest, Long storeId);
}



