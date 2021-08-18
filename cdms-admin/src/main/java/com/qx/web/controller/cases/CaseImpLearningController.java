package com.qx.web.controller.cases;

import java.util.List;

import com.qx.cases.domain.CaseTreatment;
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
import com.qx.cases.domain.CaseImpLearning;
import com.qx.cases.service.ICaseImpLearningService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 学习项目Controller
 * 
 * @author aaa
 * @date 2021-08-16
 */
@RestController
@RequestMapping("/learning/learning")
public class CaseImpLearningController extends BaseController
{
    @Autowired
    private ICaseImpLearningService caseImpLearningService;

    /**
     * 查询学习项目列表
     */
    @PreAuthorize("@ss.hasPermi('learning:learning:list')")
    @GetMapping("/list")
    public AjaxResult list(CaseImpLearning caseImpLearning)
    {
//        startPage();
        List<CaseImpLearning> list = caseImpLearningService.selectCaseImpLearningList(caseImpLearning);
        return AjaxResult.success(caseImpLearningService.buildLearningTree(list));
    }

    @GetMapping("/treeselect")
    public AjaxResult treeselect(CaseImpLearning caseImpLearning)
    {
        List<CaseImpLearning> caseImpLearnings = caseImpLearningService.selectCaseImpLearningList(caseImpLearning);
        return AjaxResult.success(caseImpLearningService.buildLearningTreeSelect(caseImpLearnings));
    }
    /**
     * 导出学习项目列表
     */
    @PreAuthorize("@ss.hasPermi('learning:learning:export')")
    @Log(title = "学习项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseImpLearning caseImpLearning)
    {
        List<CaseImpLearning> list = caseImpLearningService.selectCaseImpLearningList(caseImpLearning);
        ExcelUtil<CaseImpLearning> util = new ExcelUtil<CaseImpLearning>(CaseImpLearning.class);
        return util.exportExcel(list, "learning");
    }

    /**
     * 获取学习项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('learning:learning:query')")
    @GetMapping(value = "/{learningId}")
    public AjaxResult getInfo(@PathVariable("learningId") Long learningId)
    {
        return AjaxResult.success(caseImpLearningService.selectCaseImpLearningById(learningId));
    }

    /**
     * 新增学习项目
     */
    @PreAuthorize("@ss.hasPermi('learning:learning:add')")
    @Log(title = "学习项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseImpLearning caseImpLearning)
    {
        return toAjax(caseImpLearningService.insertCaseImpLearning(caseImpLearning));
    }

    /**
     * 修改学习项目
     */
    @PreAuthorize("@ss.hasPermi('learning:learning:edit')")
    @Log(title = "学习项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseImpLearning caseImpLearning)
    {
        return toAjax(caseImpLearningService.updateCaseImpLearning(caseImpLearning));
    }

    /**
     * 删除学习项目
     */
    @PreAuthorize("@ss.hasPermi('learning:learning:remove')")
    @Log(title = "学习项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{learningId}")
    public AjaxResult remove(@PathVariable Long learningId)
    {
        if (caseImpLearningService.hasChildById(learningId)){
            return AjaxResult.error("存在子项目,不允许删除");

        }
        return toAjax(caseImpLearningService.deleteCaseImpLearningById(learningId));
    }
}
