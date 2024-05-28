package umc.spring.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.api.request.ReviewRequest;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.ReviewResponse;
import umc.spring.api.response.StoreResponse;
import umc.spring.api.response.common.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponse.AddResultDTO> addStore(
            @ExistRegion @PathVariable Long regionId,
            @RequestBody @Valid StoreRequest.AddDto addRequest
    ){
        Store store = storeCommandService.addStore(addRequest, regionId);
        return ApiResponse.onSuccess(StoreConverter.toAddResultDTO(store));
    }

    @PostMapping("/{storeId}/review")
    public ApiResponse<ReviewResponse.AddResultDTO> addReview(
            @ExistStore @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequest.AddDto reviewRequest
    ){
        Long userId = 1L;
        Review review = reviewCommandService.addReview(reviewRequest, userId, storeId);
        return ApiResponse.onSuccess(ReviewConverter.toAddResultDTO(review));
    }
}