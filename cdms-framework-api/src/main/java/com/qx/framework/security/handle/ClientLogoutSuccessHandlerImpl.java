package com.qx.framework.security.handle;

import com.alibaba.fastjson.JSON;
import com.qx.common.constant.Constants;
import com.qx.common.constant.HttpStatus;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.utils.ServletUtils;
import com.qx.common.utils.StringUtils;
import com.qx.framework.manager.AsyncManager;
import com.qx.framework.manager.factory.AsyncFactory;
import com.qx.framework.security.LoginUser;
import com.qx.framework.security.service.TokenService;
import com.qx.student.domain.vo.LoginStudent;
import com.qx.student.util.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义退出处理类 返回成功
 * 
 * @author patient
 */
@Configuration
public class ClientLogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginStudent loginUser = tokenUtil.getLoginStudent(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenUtil.delLoginStudent(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
