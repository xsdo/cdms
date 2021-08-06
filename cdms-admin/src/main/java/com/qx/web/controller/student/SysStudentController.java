package com.qx.web.controller.student;

import java.util.Date;
import java.util.List;

import com.qx.common.constant.UserConstants;
import com.qx.common.enums.UserStatus;
import com.qx.common.exception.CustomException;
import com.qx.common.utils.StringUtils;
import com.qx.framework.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.qx.student.domain.ZStudent;
import com.qx.student.service.IZStudentService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;


/**
 * 学生管理Controller
 * 
 * @author aaa
 * @date 2021-05-18
 */
@RestController
@RequestMapping("/student/student")
public class SysStudentController extends BaseController
{
    @Autowired
    private IZStudentService zStudentService;

    /**
     * 查询学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('student:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZStudent zStudent)
    {
        startPage();
        List<ZStudent> list = zStudentService.selectZStudentList(zStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('student:student:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ZStudent zStudent)
    {
        List<ZStudent> list = zStudentService.selectZStudentList(zStudent);
        ExcelUtil<ZStudent> util = new ExcelUtil<ZStudent>(ZStudent.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 获取学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:student:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(zStudentService.selectZStudentById(id));
    }


    /**
     * 新增学生管理
     */
    @PreAuthorize("@ss.hasPermi('student:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ZStudent zStudent) {
        if (UserConstants.NOT_UNIQUE.equals(zStudentService.checkSnoUnique(zStudent.getSno())))
        {
            return AjaxResult.error("新增学生'" + zStudent.getSno() + "'失败，该学号已存在");
        }
        zStudent.setCreateBy(SecurityUtils.getUsername());
        zStudent.setPassword(SecurityUtils.encryptPassword(zStudent.getPassword()));

        return toAjax(zStudentService.insertZStudent(zStudent));
    }

    /**
     * 修改学生管理
     */
    @PreAuthorize("@ss.hasPermi('student:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ZStudent zStudent)
    {

        zStudent.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(zStudentService.updateZStudent(zStudent));
    }

    /**
     * 删除学生管理
     */
    @PreAuthorize("@ss.hasPermi('student:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(zStudentService.deleteZStudentByIds(ids));
    }
    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('student:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody ZStudent student)
    {
        student.setPassword(SecurityUtils.encryptPassword(student.getPassword()));
        student.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(zStudentService.resetPwd(student));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('student:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody ZStudent student)
    {
        student.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(zStudentService.updateStuStatus(student));
    }
    /**
     * 导入学生excel模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<ZStudent> util = new ExcelUtil<ZStudent>(ZStudent.class);
        return util.importTemplateExcel("学生数据");
    }
    /**
     * 导入学生
     */
    @PreAuthorize("@ss.hasPermi('student:student:import')")
    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public AjaxResult importStu(MultipartFile file) throws Exception
    {
        ExcelUtil<ZStudent> util = new ExcelUtil<ZStudent>(ZStudent.class);
        List<ZStudent> list = util.importExcel(file.getInputStream());
        if (StringUtils.isNull(list) || list.size() == 0) {
            throw new CustomException("导入学生数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (ZStudent student : list)
        {
            try{
                if (UserConstants.UNIQUE.equals(zStudentService.checkSnoUnique(student.getSno()))) {
                    student.setCreateBy(SecurityUtils.getUsername());
                    student.setPassword(SecurityUtils.encryptPassword("123456"));
                    zStudentService.insertZStudent(student);
                    successNum++;
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + ".学生账号 " + student.getSno() + " 已存在");
                }
            }catch (Exception e){
                failureNum++;
                String msg = "<br/>" + failureNum + ".学生账号" + student.getSno() + " 导入失败：";
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
