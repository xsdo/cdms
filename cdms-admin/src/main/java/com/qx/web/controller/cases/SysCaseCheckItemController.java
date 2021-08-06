package com.qx.web.controller.cases;

import java.util.List;

import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICasePatientItemService;
import com.qx.framework.util.SecurityUtils;
import org.apache.ibatis.annotations.Case;
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
import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.service.ICaseCheckItemService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 检查项目Controller
 * 
 * @author aaa
 * @date 2021-05-26
 */
@RestController
@RequestMapping("/cases/item")
public class SysCaseCheckItemController extends BaseController
{
    @Autowired
    private ICaseCheckItemService caseCheckItemService;

    /**
     * 查询检查项目列表
     */
    @PreAuthorize("@ss.hasPermi('cases:item:list')")
    @GetMapping("/list")
    public AjaxResult list(CaseCheckItem caseCheckItem)
    {
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(caseCheckItem);
        List<CaseCheckItem> caseCheckItems = caseCheckItemService.buildItemTree(list);
        return AjaxResult.success(caseCheckItems);
    }


    @GetMapping("/treeselect")
    public AjaxResult treeselect(CaseCheckItem item)
    {
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(item);
        return AjaxResult.success(caseCheckItemService.buildItemTreeSelect(list));
    }
    /**
     * 导出检查项目列表
     */
    @PreAuthorize("@ss.hasPermi('cases:item:export')")
    @Log(title = "检查项目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaseCheckItem caseCheckItem)
    {
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(caseCheckItem);
        ExcelUtil<CaseCheckItem> util = new ExcelUtil<CaseCheckItem>(CaseCheckItem.class);
        return util.exportExcel(list, "item");
    }

    /**
     * 获取检查项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('cases:item:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return AjaxResult.success(caseCheckItemService.selectCaseCheckItemById(itemId));
    }

    /**
     * 新增检查项目
     */
    @PreAuthorize("@ss.hasPermi('cases:item:add')")
    @Log(title = "检查项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseCheckItem caseCheckItem)
    {
        caseCheckItem.setCreateBy(SecurityUtils.getUsername());
        return toAjax(caseCheckItemService.insertCaseCheckItem(caseCheckItem));
    }

    /**
     * 修改检查项目
     */
    @PreAuthorize("@ss.hasPermi('cases:item:edit')")
    @Log(title = "检查项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseCheckItem caseCheckItem)
    {
        caseCheckItem.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(caseCheckItemService.updateCaseCheckItem(caseCheckItem));
    }

    /**
     * 删除检查项目
     */
    @PreAuthorize("@ss.hasPermi('cases:item:remove')")
    @Log(title = "检查项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemId}")
    public AjaxResult remove(@PathVariable Long itemId)
    {
        if (caseCheckItemService.hasChildByItemId(itemId))
        {
            return AjaxResult.error("存在子项目,不允许删除");
        }
//        if (caseCheckItemService.checkTypeExistQuestion(itemId))
//        {
//            return AjaxResult.error("项目已分配,不允许删除");
//        }
        return toAjax(caseCheckItemService.deleteCaseCheckItemById(itemId));
    }
}
