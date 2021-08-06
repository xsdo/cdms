package com.qx.web.controller.system;

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
import com.qx.system.domain.SysAttachment;
import com.qx.system.service.ISysAttachmentService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 附件Controller
 * 
 * @author patient
 * @date 2019-12-13
 */
@RestController
@RequestMapping("/system/attachment")
public class SysAttachmentController extends BaseController
{
    @Autowired
    private ISysAttachmentService sysAttachmentService;

    /**
     * 查询附件列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAttachment sysAttachment)
    {
        startPage();
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:export')")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysAttachment sysAttachment)
    {
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        ExcelUtil<SysAttachment> util = new ExcelUtil<SysAttachment>(SysAttachment.class);
        return util.exportExcel(list, "attachment");
    }

    /**
     * 获取附件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysAttachmentService.selectSysAttachmentById(id));
    }

    /**
     * 新增附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:add')")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.insertSysAttachment(sysAttachment));
    }

    /**
     * 修改附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:edit')")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.updateSysAttachment(sysAttachment));
    }

    /**
     * 删除附件
     */
    @PreAuthorize("@ss.hasPermi('system:attachment:remove')")
    @Log(title = "附件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysAttachmentService.deleteSysAttachmentByIds(ids));
    }
}
