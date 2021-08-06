package com.qx.web.controller.cases;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qx.common.config.ProjectConfig;
import com.qx.common.utils.file.FileUploadUtils;
import com.qx.framework.config.ServerConfig;
import com.qx.framework.util.SecurityUtils;
import com.qx.system.domain.SysNation;
import com.qx.system.service.ISysNationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.qx.cases.domain.CasePatient;
import com.qx.cases.service.ICasePatientService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;


/**
 * 案例患者Controller
 * 
 * @author aaa
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/cases/patient")
public class SysCasePatientController extends BaseController
{
    @Autowired
    private ICasePatientService casePatientService;

    @Autowired
    private ISysNationService sysNationService;

    @Autowired
    private ServerConfig serverConfig;
    /**
     * 查询案例患者列表
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(CasePatient casePatient)
    {
        startPage();
        List<CasePatient> list = casePatientService.selectCasePatientList(casePatient);
        return getDataTable(list);
    }

    /**
     * 导出案例患者列表
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:export')")
    @Log(title = "案例患者", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CasePatient casePatient)
    {
        List<CasePatient> list = casePatientService.selectCasePatientList(casePatient);
        ExcelUtil<CasePatient> util = new ExcelUtil<CasePatient>(CasePatient.class);
        return util.exportExcel(list, "patient");
    }

    /**
     * 获取案例患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(casePatientService.selectCasePatientById(id));
    }

    /**
     * 新增案例患者
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:add')")
    @Log(title = "案例患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CasePatient casePatient)
    {
        casePatient.setCreateBy(SecurityUtils.getUsername());
        return toAjax(casePatientService.insertCasePatient(casePatient));
    }

    /**
     * 修改案例患者
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:edit')")
    @Log(title = "案例患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CasePatient casePatient)
    {
        casePatient.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(casePatientService.updateCasePatient(casePatient));
    }

    /**
     * 删除案例患者
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:remove')")
    @Log(title = "案例患者", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(casePatientService.deleteCasePatientByIds(ids));
    }

    /**
     * 获取所有民族
     * @param sysNation
     * @return
     */
    @GetMapping("/nationList")
    public AjaxResult list(SysNation sysNation)
    {
        startPage();
        List<Map<String,String>> map = sysNationService.selectSysNationList(sysNation);
        return AjaxResult.success(map);
    }


}
