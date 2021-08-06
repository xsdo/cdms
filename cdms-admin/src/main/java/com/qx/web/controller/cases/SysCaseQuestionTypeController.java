package com.qx.web.controller.cases;

import java.util.Date;
import java.util.List;

import com.qx.framework.util.SecurityUtils;
import com.qx.system.domain.SysMenu;
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
import com.qx.cases.domain.CaseQuestionType;
import com.qx.cases.service.ICaseQuestionTypeService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 问答所属类型Controller
 * 
 * @author aaa
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/cases/question/type")
public class SysCaseQuestionTypeController extends BaseController
{
    @Autowired
    private ICaseQuestionTypeService caseQuestionTypeService;

    /**
     * 查询问答所属类型列表
     */
    @PreAuthorize("@ss.hasPermi('cases:type:list')")
    @GetMapping("/list")
    public AjaxResult list(CaseQuestionType caseQuestionType)
    {
        List<CaseQuestionType> list = caseQuestionTypeService.selectCaseQuestionTypeList(caseQuestionType);
        return AjaxResult.success(caseQuestionTypeService.buildTypeTree(list));
    }

    @GetMapping("/treeselect")
    public AjaxResult treeselect(CaseQuestionType type)
    {
        List<CaseQuestionType> types = caseQuestionTypeService.selectCaseQuestionTypeList(type);
        return AjaxResult.success(caseQuestionTypeService.buildTypeTreeSelect(types));
    }
    /**
     * 导出问答所属类型列表
     */
    @PreAuthorize("@ss.hasPermi('cases:type:export')")
    @Log(title = "问答所属类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseQuestionType caseQuestionType)
    {
        List<CaseQuestionType> list = caseQuestionTypeService.selectCaseQuestionTypeList(caseQuestionType);
        ExcelUtil<CaseQuestionType> util = new ExcelUtil<CaseQuestionType>(CaseQuestionType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取问答所属类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(caseQuestionTypeService.selectCaseQuestionTypeById(id));
    }

    /**
     * 新增问答所属类型
     */
    @PreAuthorize("@ss.hasPermi('cases:type:add')")
    @Log(title = "问答所属类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseQuestionType caseQuestionType)
    {
        caseQuestionType.setCreateBy(SecurityUtils.getUsername());
        return toAjax(caseQuestionTypeService.insertCaseQuestionType(caseQuestionType));
    }

    /**
     * 修改问答所属类型
     */
    @PreAuthorize("@ss.hasPermi('cases:type:edit')")
    @Log(title = "问答所属类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseQuestionType caseQuestionType)
    {
        caseQuestionType.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(caseQuestionTypeService.updateCaseQuestionType(caseQuestionType));
    }

    /**
     * 删除问答所属类型
     */
    @PreAuthorize("@ss.hasPermi('cases:type:remove')")
    @Log(title = "问答所属类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        if (caseQuestionTypeService.hasChildById(id))
        {
            return AjaxResult.error("存在子类型,不允许删除");
        }
        if (caseQuestionTypeService.checkTypeExistQuestion(id))
        {
            return AjaxResult.error("类型已分配,不允许删除");
        }
        return toAjax(caseQuestionTypeService.deleteCaseQuestionTypeById(id));
    }
}
