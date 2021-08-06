package com.qx.framework.security.filter;

import com.qx.common.utils.StringUtils;
import com.qx.framework.security.LoginUser;
import com.qx.framework.security.service.TokenService;
import com.qx.framework.util.SecurityUtils;
import com.qx.student.domain.vo.LoginStudent;
import com.qx.student.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 * 
 * @author patient
 */
@Component
public class ClientJwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenUtil tokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {

        LoginStudent loginStudent = tokenUtil.getLoginStudent(request);
        if (StringUtils.isNotNull(loginStudent) && StringUtils.isNull(SecurityUtils.getAuthentication())){
            tokenUtil.verifyToken(loginStudent);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginStudent,null,loginStudent.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }


}
