package com.market.flutter.api.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.market.flutter.api.models.dto.ApiResponse;
import com.market.flutter.api.models.dto.LoginRequest;
import com.market.flutter.api.models.dto.LoginResponse;
import com.market.flutter.api.services.security.JwtTokenUtilService;
import com.market.flutter.api.services.security.JwtUserDetailsService;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
public class AuthenticationController extends BaseController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtilService jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("auth/login")
    public ApiResponse<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.user(), loginRequest.password());

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.user());
        String token = jwtTokenUtil.generateToken(userDetails);

        return success(new LoginResponse(token));
    }

    @PostMapping("auth/encode_password")
    public ApiResponse<LoginResponse> encodePassword(@RequestBody LoginRequest loginRequest) {
        return success(new LoginResponse(passwordEncoder.encode(loginRequest.password())));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // TODO throw proper exception+error msg here and
            // handle it on the RestExceptionHandler
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
