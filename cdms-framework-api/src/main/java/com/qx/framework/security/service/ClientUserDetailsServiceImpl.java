package com.qx.framework.security.service;

import com.qx.common.enums.UserStatus;
import com.qx.common.exception.BaseException;
import com.qx.common.utils.StringUtils;
import com.qx.framework.security.LoginUser;
import com.qx.student.domain.ZStudent;
import com.qx.student.domain.vo.LoginStudent;
import com.qx.student.service.IZStudentService;
import com.qx.system.domain.SysUser;
import com.qx.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证处理
 *
 * @author suhp
 */
@Service("ClientUserDetailsServiceImpl")
public class ClientUserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(ClientUserDetailsServiceImpl.class);

    @Autowired
    private IZStudentService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        ZStudent user = userService.selectZStudentBySno(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(ZStudent user)
    {
        return new LoginStudent(user);
    }
}
