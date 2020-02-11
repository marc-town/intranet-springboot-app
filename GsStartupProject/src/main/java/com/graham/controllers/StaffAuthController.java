package com.graham.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ログイン情報コントローラ
 * 
 */
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class StaffAuthController {

}
