package com.noodles.refer.auth.constant;

/**
 * @author Noodles
 * @since 2022-11-17 16:21
 */
public final class SecurityConstant {

    /*** 角色的key **/
    public static final String ROLE_CLAIMS = "role";

    /*** 默认过期时间为1天 */
    public static final long EXPIRATION = 60 * 60 * 24L;

    /*** JWT签名密钥 */
    public static final String JWT_SECRET_KEY = "FaMDPYVVf%#lcVWj-G+6cASbA_Kcf52jAqyEO$mmntOcVQELX6$rhFV0DqYwDVEIGm@Zn97&Xt@eNtgA";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    // Swagger
    public static final String[] SWAGGER_APIS = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
            "/doc.html"
    };

    // System
    public static final String[] SYSTEM_APIS = {
            "/auth/login",
            "/user/register"
    };

}
