package com.graham.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 認証情報を管理するコントローラ
 * 
 */
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class AuthController {

	@GetMapping(value = "/public")
	public String publicApi() {
		return "this is public";
	}
	
	@GetMapping(value = "/private")
	public String authApi() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // JWTAuthenticationFilter#successfulAuthenticationで設定したusernameを取り出す
        String userName = (String) (authentication.getPrincipal());
		return "" + userName;
	}
}
