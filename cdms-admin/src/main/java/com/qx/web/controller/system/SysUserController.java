package com.qx.web.controller.system;

import java.util.Date;
import java.util.List;

import com.qx.common.enums.UserStatus;
import com.qx.common.exception.CustomException;
import com.qx.student.domain.ZStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.qx.common.annotation.Log;
import com.qx.common.constant.UserConstants;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.enums.UserType;
import com.qx.common.utils.StringUtils;
import com.qx.common.utils.poi.ExcelUtil;
import com.qx.framework.security.LoginUser;
import com.qx.framework.util.SecurityUtils;
import com.qx.system.domain.SysUser;
import com.qx.system.service.ISysPostService;
import com.qx.system.service.ISysRoleService;
import com.qx.system.service.ISysUserService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户信息
 * 
 * @author patient
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        user.setUserType(UserType.system.getCode());
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    
    /**
     * 查询用户列表
     */
    @GetMapping("/getList")
    public AjaxResult getList(SysUser user){
    	List<SysUser> list = userService.selectUserList(user);
        return AjaxResult.success(list);
    }
    
    
    /**
     * 查询部门用户列表
     */
    @GetMapping("/getUserByDeptList")
    public AjaxResult getUserByDeptList(Long deptId){
    	SysUser su=new SysUser();
    	if(null==deptId) {
    		LoginUser user=SecurityUtils.getLoginUser();
    		su.setDeptId(user.getUser().getDeptId());
    	}else {
    		su.setDeptId(deptId);
    	}
    	List<SysUser> list = userService.selectUserList(su);
        return AjaxResult.success(list);
    }
    
    
    
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("roles", roleService.selectRoleAll());
//        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
//            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUserType(UserType.system.getCode());
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);

        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }
    /**
     * 导入excel模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }
    /**
     * 导入用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult importU(MultipartFile file) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> list = util.importExcel(file.getInputStream());
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (SysUser user : list)
        {
            try{
                if (UserConstants.UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
                    user.setCreateBy(SecurityUtils.getUsername());
                    user.setPassword(SecurityUtils.encryptPassword("123456"));
                    user.setUserType(UserType.system.getCode());
                    userService.insertUser(user);
                    successNum++;
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + ".用户账号 " + user.getUserName() + " 已存在");
                }
            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + ".用户账号" + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }

        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入出现错误！共 " +(successNum+failureNum) + " 条数据，其中 "+failureNum+" 条格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }

        return AjaxResult.success(successMsg.toString());
    }
}