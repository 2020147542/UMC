package umc.spring.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.api.request.StoreRequest;
import umc.spring.api.response.MemberResponse;
import umc.spring.api.response.common.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    @PostMapping
    public ApiResponse<StoreRequest.AddDto> add()
}
