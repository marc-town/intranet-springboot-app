package com.graham.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 認証情報を管理するコントローラ
 * 
 */
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class StaffAuthController {

}
