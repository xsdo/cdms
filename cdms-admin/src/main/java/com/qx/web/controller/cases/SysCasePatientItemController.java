package com.qx.web.controller.cases;

import java.util.List;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.service.ICaseCheckItemService;
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
import com.qx.cases.domain.CasePatientItem;
import com.qx.cases.service.ICasePatientItemService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 案例患者和检查项目关联Controller
 * 
 * @author aaa
 * @date 2021-05-27
 */
@RestController
@RequestMapping("/cases/patient/item")
public class SysCasePatientItemController extends BaseController
{
    @Autowired
    private ICasePatientItemService casePatientItemService;
    @Autowired
    private ICaseCheckItemService caseCheckItemService;



    /**
     * 查询某案例患者下的检查项目列表
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:list')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getListByPatientId(@PathVariable("patientId") Long patientId,CaseCheckItem caseCheckItem)
    {
        List<CaseCheckItem> list = caseCheckItemService.selectCaseCheckItemList(caseCheckItem);
        for (CaseCheckItem item :list){
            CasePatientItem patientItem = casePatientItemService.selectCasePatientItemById(patientId, item.getItemId());
            if (patientItem!=null){
                //获取该案例下对应的检查项目数据
                item.setPatientItem(patientItem);
            }
        }
        return AjaxResult.success(caseCheckItemService.buildItemTree(list));
    }

    /**
     * 查询案例患者的检查项目数据
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:query')")
    @GetMapping("/getInfo")
    public AjaxResult getInfo(Long patientId, Long itemId)
    {
        CasePatientItem patientItem = casePatientItemService.selectCasePatientItemById(patientId, itemId);
        return  AjaxResult.success(patientItem);
    }

    /**
     * 新增案例患者和检查项目关联
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:add')")
    @Log(title = "案例患者和检查项目关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CasePatientItem casePatientItem)
    {
        return toAjax(casePatientItemService.insertCasePatientItem(casePatientItem));
    }

    /**
     * 修改案例患者和检查项目关联
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:edit')")
    @Log(title = "案例患者和检查项目关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CasePatientItem casePatientItem)
    {
        return toAjax(casePatientItemService.updateCasePatientItem(casePatientItem));
    }

    /**
     * 删除案例患者和检查项目关联
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:remove')")
    @Log(title = "案例患者和检查项目关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/delete")
    public AjaxResult remove(Long patientId, Long itemId)
    {
        return toAjax(casePatientItemService.deleteCasePatientItem(patientId,itemId));
    }

    /**
     * 导出案例患者和检查项目关联列表
     */
    @PreAuthorize("@ss.hasPermi('cases:patient:export')")
    @Log(title = "案例患者和检查项目关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CasePatientItem casePatientItem)
    {
        List<CasePatientItem> list = casePatientItemService.selectCasePatientItemList(casePatientItem);
        ExcelUtil<CasePatientItem> util = new ExcelUtil<CasePatientItem>(CasePatientItem.class);
        return util.exportExcel(list, "item");
    }
}
