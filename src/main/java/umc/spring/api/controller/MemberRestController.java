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
import umc.spring.api.response.MissionResponse;
import umc.spring.api.response.ReviewResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.Review;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.api.request.MemberRequest;
import umc.spring.api.response.MemberResponse;
import umc.spring.api.response.common.ApiResponse;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMission;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping
    public ApiResponse<MemberResponse.JoinResultDTO> join(
            @RequestBody @Valid MemberRequest.JoinDto joinRequest
            // @Valid 어노테이션이 존재하므로 @ExistCategories에서 발생한 예외가 바로 전달이 되지 않고,
            // @Valid 어노테이션이 **MethodArgumentNotValidException를 발생시킵니다.**
        ) {
        Member member = memberCommandService.joinMember(joinRequest);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions/{missionId}")
    public ApiResponse<String> missionStart(
            @ExistMission @PathVariable("missionId") Long missionId
    ){
        memberCommandService.missionAdd(missionId,1L);
        return ApiResponse.onSuccess("mission started");
    }

    @PatchMapping("/missions/{missionId}")
    @Operation(summary = "진행중인 미션 완료로 바꾸기 API",description = "진행중인 미션을 완료상태로 바꾸는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<String> changeMissionStatus(
            @ExistMission @PathVariable("missionId") Long missionId
    ){
        memberCommandService.missionState(missionId, 1L);
        return ApiResponse.onSuccess("mission status changed");
    }

    @GetMapping("/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API",description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<ReviewResponse.ReviewListDTO> getMyReviews(
            @CheckPage @RequestParam Integer page
    ){
        Page<Review> reviewPage =  memberQueryService.getMemberReviews(1L, page);

        return ApiResponse.onSuccess(ReviewConverter.toReviewListDTO(reviewPage));
    }

    @GetMapping("/missions/progress")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API",description = "내가 진행하는 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MissionResponse.MissionListDTO> getMyProgressMissions(
            @CheckPage @RequestParam Integer page
    ){
        Page<MemberMission> missionPage = memberQueryService.getMemberProgressMission(1L, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionListDTOFromMemberMission(missionPage));
    }

    @GetMapping("/missions/complete")
    @Operation(summary = "내가 완료한 미션 목록 조회 API",description = "내가 완료한 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MissionResponse.MissionListDTO> getMyCompleteMissions(
            @CheckPage @RequestParam Integer page
    ){
        Page<MemberMission> missionPage = memberQueryService.getMemberCompleteMission(1L, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionListDTOFromMemberMission(missionPage));
    }
}