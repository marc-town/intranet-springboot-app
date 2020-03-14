package com.graham.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graham.interfaces.request.JwtRequestForm;
import com.graham.interfaces.request.SignupRequestForm;
import com.graham.interfaces.response.JwtResponseForm;
import com.graham.security.JwtSecurityConstants;
import com.graham.security.JwtTokenProvider;
import com.graham.security.UserPrincipal;
import com.graham.services.StaffService;

/**
 * 認証情報を管理するコントローラ
 * 
 */
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
	
	@Autowired(required=true)
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	@Autowired
	private StaffService staffService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/signin")
	public JwtResponseForm authenticateStaff(@Valid @RequestBody JwtRequestForm request) {

		LOGGER.info("BEGIN AuthController authenticateStaff");
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = JwtTokenProvider.generateJwtToken(authentication);
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();		
		List<String> roles = userPrincipal.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponseForm(jwt, userPrincipal.getStaffId(), userPrincipal.getUsername(), roles, JwtSecurityConstants.EXPIRATION_TIME);
	}
	
	@PostMapping("/signup")
	public void registerStaff(@Valid @RequestBody SignupRequestForm request) {
		LOGGER.info("BEGIN AuthController registerStaff");
		staffService.regist(request);
	}
}
