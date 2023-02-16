package com.market.flutter.api.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PostMapping("auth/login")
    public ApiResponse<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) throws Exception {

        authenticate(loginRequest.getUser(), loginRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUser());
        String token = jwtTokenUtil.generateToken(userDetails);

        return success(new LoginResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
