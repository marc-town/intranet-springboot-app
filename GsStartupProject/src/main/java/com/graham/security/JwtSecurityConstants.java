package com.graham.security;

public class JwtSecurityConstants {

	public static final String SECRET_KEY = "grahamsolutionSecretKey";
    public static final long EXPIRATION_TIME = 1_800_000; // 30min
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String SIGNUP_URL = "/staffs";
    public static final String SIGNIN_URL = "/auth/signin";
    public static final String SIGNIN_ID = "loginId";
    public static final String PASSWORD = "password";
}
