package com.graham.common.auth;

public class SecurityConstants {

	public static final String CRYPT_KEY = "grahamsamplesecret";
    public static final long EXPIRATION_TIME = 28_800_000; // 8hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String SIGNUP_URL = "/staffs";
    public static final String SIGNIN_URL = "/login";
    public static final String SIGNIN_ID = "loginId";
    public static final String PASSWORD = "password";
}
