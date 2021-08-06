package com.qx.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qx.common.annotation.Log;
import com.qx.common.constant.UserConstants;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.enums.BusinessType;
import com.qx.framework.security.LoginUser;
import com.qx.framework.util.SecurityUtils;
import com.qx.system.domain.SysDept;
import com.qx.system.service.ISysDeptService;

/**
 * 学院/系部信息
 * 
 * @author patient
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    @Autowired
    private ISysDeptService deptService;

    /**
     * 获取学院/系部列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public AjaxResult list(SysDept dept)
    {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTree(depts));
    }

    /**
     * 根据学院/系部编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable Long deptId)
    {
        return AjaxResult.success(deptService.selectDeptById(deptId));
    }
    
    
    /**
     * 查询下级学院/系部列表
     */
    @GetMapping("/treeLowerDeptList")
    public AjaxResult treeLowerDeptList(Long deptId){
    	SysDept dept=new SysDept();
    	if(null==deptId) {
    		LoginUser user=SecurityUtils.getLoginUser();
    		dept.setParentId(user.getUser().getDeptId());
    	}else {
    		dept.setParentId(deptId);
    	}
    	List<SysDept> list = deptService.selectDeptList(dept);
        return AjaxResult.success(list);
    }
    
    /**
     * 查询下级学院/系部列表
     */
    @GetMapping("/getLowerDeptTree")
    public AjaxResult getLowerDeptTree(Long deptId){
    	if(null==deptId) {
    		LoginUser user=SecurityUtils.getLoginUser();
    		deptId=user.getUser().getDeptId();
    	}
        return AjaxResult.success(deptService.buildLowerDeptTree(deptId));
    }
    
    /**
     * 获取学院/系部下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysDept dept)
    {
        List<SysDept> depts = deptService.selectDeptList(dept);
        return AjaxResult.success(deptService.buildDeptTreeSelect(depts));
    }
    
    /**
     * 学院/系部和人数
     * @param
     * @return
     */
    @GetMapping("/treeDeptAndUser")
    public AjaxResult treeDeptAndUser() {
    	LoginUser user=SecurityUtils.getLoginUser();
    	SysDept dept=deptService.selectDeptById(user.getUser().getDeptId());
    	return AjaxResult.success(deptService.buildDeptAndUserTreeSelect(dept));
    }
    
    
    /**
     * 加载对应角色学院/系部列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId)
    {
        return AjaxResult.success(deptService.selectDeptListByRoleId(roleId));
    }

    /**
     * 新增学院/系部
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "学院/系部管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDept dept)
    {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("新增学院/系部'" + dept.getDeptName() + "'失败，学院/系部名称已存在");
        }
        dept.setCreateBy(SecurityUtils.getUsername());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改学院/系部
     */
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "学院/系部管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDept dept)
    {
        if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return AjaxResult.error("修改学院/系部'" + dept.getDeptName() + "'失败，学院/系部名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return AjaxResult.error("修改学院/系部'" + dept.getDeptName() + "'失败，上级学院/系部不能是自己");
        }
        dept.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除学院/系部
     */
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "学院/系部管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptId}")
    public AjaxResult remove(@PathVariable Long deptId)
    {
        if (deptService.hasChildByDeptId(deptId))
        {
            return AjaxResult.error("存在下级学院/系部,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.error("学院/系部存在用户,不允许删除");
        }
        if (deptService.checkDeptExistStudent(deptId))
        {
            return AjaxResult.error("学院/系部存在学生,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }
}
