package com.example.shopdemo.controller;

import com.example.shopdemo.auth.AuthRequest;
import com.example.shopdemo.auth.AuthResponse;
import com.example.shopdemo.service.AuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    @Operation(
            description = "Sign In Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully Singed In!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":200,\"Status\": \"Ok\",\"Message\":\"Successfully signed in!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            ref = "badRequestAPI"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            ref = "internalServerErrorAPI"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Username or Password is correct!",
                            content = @Content(
                                    mediaType = "Application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"Code\":403,\"Status\": \"Not Ok\",\"Message\":\"Username or Password is correct!\"}"
                                            )
                                    }
                            )
                    ),
            }
    )
    private ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }


}
