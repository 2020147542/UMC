package umc.spring.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.api.request.MissionRequest;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.MissionResponse;
import umc.spring.api.response.ReviewResponse;
import umc.spring.api.response.StoreResponse;
import umc.spring.api.response.common.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponse.AddResultDTO> addStore(
            @ExistRegion @PathVariable("regionId") Long regionId,
            @RequestBody @Valid StoreRequest.StoreAddDto addRequest
    ){
        Store store = storeCommandService.addStore(addRequest, regionId);
        return ApiResponse.onSuccess(StoreConverter.toAddResultDTO(store));
    }

    @PostMapping("/{storeId}/review")
    public ApiResponse<ReviewResponse.AddResultDTO> addReview(
            @ExistStore @PathVariable("storeId") Long storeId,
            @RequestBody @Valid ReviewRequest.ReviewAddDto reviewRequest
    ){
        Long userId = 1L;
        Review review = storeCommandService.addReview(reviewRequest, userId, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponse.AddResultDTO> addMission(
            @ExistStore @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionRequest.MissionAddDto missionRequest
    ){
        Mission mission = storeCommandService.addMission(missionRequest, storeId);
        return ApiResponse.onSuccess(MissionConverter.toAddResultDTO(mission));
    }
}