package com.qx.web.controller.cases;

import java.util.Date;
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
import com.qx.cases.domain.CaseQuestion;
import com.qx.cases.service.ICaseQuestionService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 问诊问答Controller
 * 
 * @author aaa
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/cases/question")
public class SysCaseQuestionController extends BaseController
{
    @Autowired
    private ICaseQuestionService caseQuestionService;

    /**
     * 查询问诊问答列表
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseQuestion caseQuestion)
    {
        startPage();
        List<CaseQuestion> list = caseQuestionService.selectCaseQuestionList(caseQuestion);
        return getDataTable(list);
    }

    /**
     * 导出问诊问答列表
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:export')")
    @Log(title = "问诊问答", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseQuestion caseQuestion)
    {
        List<CaseQuestion> list = caseQuestionService.selectCaseQuestionList(caseQuestion);
        ExcelUtil<CaseQuestion> util = new ExcelUtil<CaseQuestion>(CaseQuestion.class);
        return util.exportExcel(list, "question");
    }

    /**
     * 获取问诊问答详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(caseQuestionService.selectCaseQuestionById(id));
    }

    /**
     * 新增问诊问答
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:add')")
    @Log(title = "问诊问答", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseQuestion caseQuestion)
    {
        caseQuestion.setCreateBy(SecurityUtils.getUsername());
        return toAjax(caseQuestionService.insertCaseQuestion(caseQuestion));
    }

    /**
     * 修改问诊问答
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:edit')")
    @Log(title = "问诊问答", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseQuestion caseQuestion)
    {
        caseQuestion.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(caseQuestionService.updateCaseQuestion(caseQuestion));
    }

    /**
     * 删除问诊问答
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:remove')")
    @Log(title = "问诊问答", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(caseQuestionService.deleteCaseQuestionByIds(ids));
    }
}
