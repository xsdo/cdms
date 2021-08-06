package com.qx.student.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

import com.qx.student.domain.vo.LoginStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qx.common.constant.Constants;
import com.qx.common.utils.IdUtils;
import com.qx.common.utils.ServletUtils;
import com.qx.common.utils.StringUtils;
import com.qx.common.utils.ip.AddressUtils;
import com.qx.common.utils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token验证处理
 * 
 * @author patient
 */
@Component
public class TokenUtil
{
    private String header="Authorizationa";

    private String secret="abcdefghijklmnopqrst";

    private int expireTime=300;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

  
    public LoginStudent getLoginStudent(HttpServletRequest request)
    {
       
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String userKey = getTokenKey(uuid);
            LoginStudent student = redisCacheUtil.getCacheObject(userKey);
            return student;
        }
        return null;
    }

    public void setLoginStudent(LoginStudent LoginStudent)
    {
        if (StringUtils.isNotNull(LoginStudent) && StringUtils.isNotEmpty(LoginStudent.getToken()))
        {
            String userKey = getTokenKey(LoginStudent.getToken());
            redisCacheUtil.setCacheObject(userKey, LoginStudent);
        }
    }

    public void delLoginStudent(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            redisCacheUtil.deleteObject(userKey);
        }
    }

    public String createToken(LoginStudent LoginStudent)
    {
        String token = IdUtils.fastUUID();
        LoginStudent.setToken(token);
        setUserAgent(LoginStudent);
        refreshToken(LoginStudent);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    public void verifyToken(LoginStudent LoginStudent)
    {
        long expireTime = LoginStudent.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            String token = LoginStudent.getToken();
            LoginStudent.setToken(token);
            refreshToken(LoginStudent);
        }
    }

    public void refreshToken(LoginStudent LoginStudent)
    {
        LoginStudent.setLoginTime(System.currentTimeMillis());
        LoginStudent.setExpireTime(LoginStudent.getLoginTime() + expireTime * MILLIS_MINUTE);
        String userKey = getTokenKey(LoginStudent.getToken());
        redisCacheUtil.setCacheObject(userKey, LoginStudent, expireTime, TimeUnit.MINUTES);
    }
    
    public void setUserAgent(LoginStudent LoginStudent)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        LoginStudent.setIpaddr(ip);
        LoginStudent.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        LoginStudent.setBrowser(userAgent.getBrowser().getName());
        LoginStudent.setOs(userAgent.getOperatingSystem().getName());
    }
    
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsernameFromToken(String token)
    {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
//        token= "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjQ4ZGQ5ZDU4LWFkZGYtNDNjZC1hMmUyLW12YxYWZjMGY5MzYyNiJ9.5p829B0Ht9_QtVS_Ev1FJWOD8WEO4XHZ0IbWbNkMjRbS6ZwuJjy1NW3LCEj1-sc6vyYdT7rL8Q6JgJtXSw0adA";
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid)
    {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }
}
