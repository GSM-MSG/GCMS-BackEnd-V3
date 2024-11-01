package com.gcms.v3.domain.auth.presentation;

import com.gcms.v3.domain.auth.presentation.data.request.SignInRequestDto;
import com.gcms.v3.domain.auth.presentation.data.response.TokenInfoResponseDto;
import com.gcms.v3.domain.auth.service.ReissueTokenService;
import com.gcms.v3.domain.auth.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v3/auth")
public class AuthController {

    private final SignInService signInService;
    private final ReissueTokenService reissueTokenService;

    @PostMapping
    public ResponseEntity<TokenInfoResponseDto> signIn (@RequestBody SignInRequestDto signInRequestDto) {
        TokenInfoResponseDto res = signInService.execute(signInRequestDto);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reissueToken")
    public ResponseEntity<TokenInfoResponseDto> reissueToken (@RequestBody String refreshToken) {
        TokenInfoResponseDto res = reissueTokenService.execute(refreshToken);
        return ResponseEntity.ok(res);
    }
}
