package com.qx.framework.security.service;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.qx.common.constant.Constants;
import com.qx.common.exception.CustomException;
import com.qx.common.exception.user.UserPasswordNotMatchException;
import com.qx.common.utils.MessageUtils;
import com.qx.framework.manager.AsyncManager;
import com.qx.framework.manager.factory.AsyncFactory;
import com.qx.framework.security.LoginUser;

/**
 * 登录校验方法
 * 
 * @author patient
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public LoginUser login(String username, String password)
    {
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return loginUser;
    }
}
