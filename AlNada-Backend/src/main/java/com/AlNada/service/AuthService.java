package com.AlNada.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.AlNada.config.JwtUtil;
import com.AlNada.entity.User;
import com.AlNada.payload.AuthRequest;
import com.AlNada.repository.UserRepository;

@Service
public class AuthService {
	
    @Autowired private UserRepository repo;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;

 
    public ResponseEntity<?> register(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return ResponseEntity.ok(repo.save(user));
    }

   
    public ResponseEntity<?> login(AuthRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = jwtUtil.generateToken(req.getUsername());
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    
    public ResponseEntity<?> getUser(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(repo.findByUsername(username).orElse(null));
    }

}
