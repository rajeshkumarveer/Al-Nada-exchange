package com.AlNada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AlNada.entity.User;
import com.AlNada.payload.AuthRequest;
import com.AlNada.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	   @Autowired private AuthService authService;

	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody User user) {
	        return ResponseEntity.ok(authService.register(user));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
	        return ResponseEntity.ok(authService.login(req));
	    }

	    @GetMapping("/user")
	    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
	        return ResponseEntity.ok(authService.getUser(authHeader));
	    }
}
