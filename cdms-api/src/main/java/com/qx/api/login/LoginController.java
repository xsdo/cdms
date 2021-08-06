package com.qx.api.login;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.qx.common.constant.Constants;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.utils.ServletUtils;
import com.qx.framework.security.LoginUser;
import com.qx.framework.security.service.SysLoginService;
import com.qx.framework.security.service.SysPermissionService;
import com.qx.framework.security.service.TokenService;
import com.qx.system.domain.SysMenu;
import com.qx.system.domain.SysUser;
import com.qx.system.service.ISysMenuService;


/**
 * 登录验证
 * 
 * @author patient
 */
@RestController
public class LoginController
{
    @Autowired(required = false)
    private SysLoginService loginService;

    @Autowired(required = false)
    private ISysMenuService menuService;

    @Autowired(required = false)
    private SysPermissionService permissionService;

    @Autowired(required = false)
    private TokenService tokenService;

    /**
     * 登录方法
     * 
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password)
    {
        LoginUser loginUser  = loginService.login(username, password);
        // 生成令牌
        String token = tokenService.createToken(loginUser);
        SysUser user = loginUser.getUser();

        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
}
