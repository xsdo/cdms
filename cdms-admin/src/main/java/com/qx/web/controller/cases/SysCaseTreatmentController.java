package com.qx.web.controller.cases;

import java.util.List;

import com.qx.cases.domain.CaseQuestionType;
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
import com.qx.cases.domain.CaseTreatment;
import com.qx.cases.service.ICaseTreatmentService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 治疗项目Controller
 * 
 * @author aaa
 * @date 2021-06-01
 */
@RestController
@RequestMapping("/cases/treatment")
public class SysCaseTreatmentController extends BaseController
{
    @Autowired
    private ICaseTreatmentService caseTreatmentService;

    /**
     * 查询治疗项目列表
     */
    @PreAuthorize("@ss.hasPermi('cases:treatment:list')")
    @GetMapping("/list")
    public AjaxResult list(CaseTreatment caseTreatment)
    {
        List<CaseTreatment> list = caseTreatmentService.selectCaseTreatmentList(caseTreatment);
        return AjaxResult.success(caseTreatmentService.buildTreatmentTree(list));
    }

    @GetMapping("/treeselect")
    public AjaxResult treeselect(CaseTreatment caseTreatment)
    {
        List<CaseTreatment> treatments = caseTreatmentService.selectCaseTreatmentList(caseTreatment);
        return AjaxResult.success(caseTreatmentService.buildTreatmentTreeSelect(treatments));
    }
    /**
     * 导出治疗项目列表
     */
    @PreAuthorize("@ss.hasPermi('cases:treatment:export')")
    @Log(title = "治疗项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseTreatment caseTreatment)
    {
        List<CaseTreatment> list = caseTreatmentService.selectCaseTreatmentList(caseTreatment);
        ExcelUtil<CaseTreatment> util = new ExcelUtil<CaseTreatment>(CaseTreatment.class);
        return util.exportExcel(list, "treatment");
    }

    /**
     * 获取治疗项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:treatment:query')")
    @GetMapping(value = "/{treatmentId}")
    public AjaxResult getInfo(@PathVariable("treatmentId") Long treatmentId)
    {
        return AjaxResult.success(caseTreatmentService.selectCaseTreatmentById(treatmentId));
    }

    /**
     * 新增治疗项目
     */
    @PreAuthorize("@ss.hasPermi('cases:treatment:add')")
    @Log(title = "治疗项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseTreatment caseTreatment)
    {
        caseTreatment.setCreateBy(SecurityUtils.getUsername());
        return toAjax(caseTreatmentService.insertCaseTreatment(caseTreatment));
    }

    /**
     * 修改治疗项目
     */
    @PreAuthorize("@ss.hasPermi('cases:treatment:edit')")
    @Log(title = "治疗项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseTreatment caseTreatment)
    {
        caseTreatment.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(caseTreatmentService.updateCaseTreatment(caseTreatment));
    }

    /**
     * 删除治疗项目
     */
    @PreAuthorize("@ss.hasPermi('cases:treatment:remove')")
    @Log(title = "治疗项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{treatmentId}")
    public AjaxResult remove(@PathVariable Long treatmentId)
    {
        if (caseTreatmentService.hasChildById(treatmentId)){
            return AjaxResult.error("存在子项目,不允许删除");

        }
        return toAjax(caseTreatmentService.deleteCaseTreatmentById(treatmentId));
    }
}
