package inu.codin.codin.domain.email.controller;

import inu.codin.codin.common.response.SingleResponse;
import inu.codin.codin.domain.email.dto.JoinEmailCheckRequestDto;
import inu.codin.codin.domain.email.dto.JoinEmailSendRequestDto;
import inu.codin.codin.domain.email.service.EmailAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
@Tag(name = "User Auth API", description = "유저 회원가입, 로그인, 로그아웃, 리이슈 API")
@RequiredArgsConstructor
public class EmailController {

    private final EmailAuthService emailAuthService;

    @Operation(summary = "이메일 인증 코드 전송 - 학교인증 X")
    @PostMapping("/auth/send")
    public ResponseEntity<SingleResponse<?>> sendJoinAuthEmail(
            @RequestBody @Valid JoinEmailSendRequestDto emailAuthRequestDto
    ) {
        emailAuthService.sendAuthEmail(emailAuthRequestDto);
        return ResponseEntity.ok()
                .body(new SingleResponse<>(200, "이메일 인증 코드 전송 성공", null));
    }

    @Operation(summary = "이메일 인증 코드 확인 - 학교인증 X")
    @PostMapping("/auth/check")
    public ResponseEntity<SingleResponse<?>> checkAuthNum(
            @RequestBody @Valid JoinEmailCheckRequestDto joinEmailCheckRequestDto
    ) {
        emailAuthService.checkAuthNum(joinEmailCheckRequestDto);
        return ResponseEntity.ok()
                .body(new SingleResponse<>(200, "이메일 인증 성공 - 회원가입 가능", null));
    }
}
