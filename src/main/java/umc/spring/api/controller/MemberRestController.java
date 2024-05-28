package umc.spring.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.api.request.MemberRequest;
import umc.spring.api.response.MemberResponse;
import umc.spring.api.response.common.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public ApiResponse<MemberResponse.JoinResultDTO> join(
            @RequestBody @Valid MemberRequest.JoinDto joinRequest
            // @Valid 어노테이션이 존재하므로 @ExistCategories에서 발생한 예외가 바로 전달이 되지 않고,
            // @Valid 어노테이션이 **MethodArgumentNotValidException를 발생시킵니다.**
        ) {
        Member member = memberCommandService.joinMember(joinRequest);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}
