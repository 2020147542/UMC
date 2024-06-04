package umc.spring.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponse.AddResultDTO> addStore(
            @ExistRegion @PathVariable("regionId") Long regionId,
            @RequestBody @Valid StoreRequest.StoreAddDto addRequest
    ){
        Store store = storeCommandService.addStore(addRequest, regionId);
        return ApiResponse.onSuccess(StoreConverter.toAddResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews")
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


    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponse.ReviewListDTO> getReviews(
            @ExistStore @PathVariable("storeId") Long storeId,
            @CheckPage @RequestParam Integer page
    ){
        Page<Review> reviewPage =  storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.toReviewListDTO(reviewPage));
    }


    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<MissionResponse.MissionListDTO> getMissions(
            @ExistStore @PathVariable("storeId") Long storeId,
            @CheckPage @RequestParam Integer page
    ){
        Page<Mission> missionPage =  storeQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionListDTO(missionPage));
    }
}

