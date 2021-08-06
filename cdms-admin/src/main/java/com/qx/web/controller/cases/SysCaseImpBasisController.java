package com.qx.web.controller.cases;

import java.util.List;
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
import com.qx.cases.domain.CaseImpBasis;
import com.qx.cases.service.ICaseImpBasisService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 诊断依据Controller
 * 
 * @author aaa
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/cases/basis")
public class SysCaseImpBasisController extends BaseController
{
    @Autowired
    private ICaseImpBasisService caseImpBasisService;

    /**
     * 查询诊断依据列表
     */
    @PreAuthorize("@ss.hasPermi('cases:basis:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseImpBasis caseImpBasis)
    {
        startPage();
        List<CaseImpBasis> list = caseImpBasisService.selectCaseImpBasisList(caseImpBasis);
        return getDataTable(list);
    }

    /**
     * 导出诊断依据列表
     */
    @PreAuthorize("@ss.hasPermi('cases:basis:export')")
    @Log(title = "诊断依据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseImpBasis caseImpBasis)
    {
        List<CaseImpBasis> list = caseImpBasisService.selectCaseImpBasisList(caseImpBasis);
        ExcelUtil<CaseImpBasis> util = new ExcelUtil<CaseImpBasis>(CaseImpBasis.class);
        return util.exportExcel(list, "basis");
    }

    /**
     * 获取诊断依据详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:basis:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(caseImpBasisService.selectCaseImpBasisById(id));
    }

    /**
     * 新增诊断依据
     */
    @PreAuthorize("@ss.hasPermi('cases:basis:add')")
    @Log(title = "诊断依据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseImpBasis caseImpBasis)
    {
        return toAjax(caseImpBasisService.insertCaseImpBasis(caseImpBasis));
    }

    /**
     * 修改诊断依据
     */
    @PreAuthorize("@ss.hasPermi('cases:basis:edit')")
    @Log(title = "诊断依据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseImpBasis caseImpBasis)
    {
        return toAjax(caseImpBasisService.updateCaseImpBasis(caseImpBasis));
    }

    /**
     * 删除诊断依据
     */
    @PreAuthorize("@ss.hasPermi('cases:basis:remove')")
    @Log(title = "诊断依据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(caseImpBasisService.deleteCaseImpBasisByIds(ids));
    }
}
