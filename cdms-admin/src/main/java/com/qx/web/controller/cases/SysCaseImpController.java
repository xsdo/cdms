package com.qx.web.controller.cases;

import java.util.List;

import com.qx.framework.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qx.cases.domain.CaseImp;
import com.qx.cases.service.ICaseImpService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 诊断库Controller
 * 
 * @author aaa
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/cases/imp")
public class SysCaseImpController extends BaseController
{
    @Autowired
    private ICaseImpService caseImpService;

    /**
     * 查询诊断库列表
     */
    @PreAuthorize("@ss.hasPermi('cases:imp:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseImp caseImp)
    {
        startPage();
        List<CaseImp> list = caseImpService.selectCaseImpList(caseImp);
        return getDataTable(list);
    }

    /**
     * 导出诊断库列表
     */
    @PreAuthorize("@ss.hasPermi('cases:imp:export')")
    @Log(title = "诊断库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseImp caseImp)
    {
        List<CaseImp> list = caseImpService.selectCaseImpList(caseImp);
        ExcelUtil<CaseImp> util = new ExcelUtil<CaseImp>(CaseImp.class);
        return util.exportExcel(list, "imp");
    }

    /**
     * 获取诊断库详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:imp:query')")
    @GetMapping(value = "/{impId}")
    public AjaxResult getInfo(@PathVariable("impId") Long impId)
    {
        return AjaxResult.success(caseImpService.selectCaseImpById(impId));
    }

    /**
     * 新增诊断库
     */
    @PreAuthorize("@ss.hasPermi('cases:imp:add')")
    @Log(title = "诊断库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseImp caseImp)
    {
        caseImp.setCreateBy(SecurityUtils.getUsername());
        return toAjax(caseImpService.insertCaseImp(caseImp));
    }

    /**
     * 修改诊断库
     */
    @PreAuthorize("@ss.hasPermi('cases:imp:edit')")
    @Log(title = "诊断库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseImp caseImp)
    {
        caseImp.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(caseImpService.updateCaseImp(caseImp));
    }

    /**
     * 删除诊断库
     */
    @PreAuthorize("@ss.hasPermi('cases:imp:remove')")
    @Log(title = "诊断库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{impIds}")
    public AjaxResult remove(@PathVariable Long[] impIds)
    {
        return toAjax(caseImpService.deleteCaseImpByIds(impIds));
    }
}
