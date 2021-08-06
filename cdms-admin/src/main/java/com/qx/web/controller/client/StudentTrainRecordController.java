package com.qx.web.controller.client;

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
import com.qx.student.domain.StudentTrainRecord;
import com.qx.student.service.IStudentTrainRecordService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 学生训练记录Controller
 * 
 * @author aaa
 * @date 2021-06-07
 */
@RestController
@RequestMapping("/client/record")
public class StudentTrainRecordController extends BaseController
{
    @Autowired
    private IStudentTrainRecordService studentTrainRecordService;

    /**
     * 查询学生训练记录列表
     */
    @PreAuthorize("@ss.hasPermi('student:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(StudentTrainRecord studentTrainRecord)
    {
        startPage();
        List<StudentTrainRecord> list = studentTrainRecordService.selectStudentTrainRecordList(studentTrainRecord);
        return getDataTable(list);
    }

    /**
     * 导出学生训练记录列表
     */
    @PreAuthorize("@ss.hasPermi('student:record:export')")
    @Log(title = "学生训练记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StudentTrainRecord studentTrainRecord)
    {
        List<StudentTrainRecord> list = studentTrainRecordService.selectStudentTrainRecordList(studentTrainRecord);
        ExcelUtil<StudentTrainRecord> util = new ExcelUtil<StudentTrainRecord>(StudentTrainRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取学生训练记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(studentTrainRecordService.selectStudentTrainRecordById(id));
    }

    /**
     * 新增学生训练记录
     */
    @PreAuthorize("@ss.hasPermi('student:record:add')")
    @Log(title = "学生训练记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StudentTrainRecord studentTrainRecord)
    {
        return toAjax(studentTrainRecordService.insertStudentTrainRecord(studentTrainRecord));
    }

    /**
     * 修改学生训练记录
     */
    @PreAuthorize("@ss.hasPermi('student:record:edit')")
    @Log(title = "学生训练记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StudentTrainRecord studentTrainRecord)
    {
        return toAjax(studentTrainRecordService.updateStudentTrainRecord(studentTrainRecord));
    }

    /**
     * 删除学生训练记录
     */
    @PreAuthorize("@ss.hasPermi('student:record:remove')")
    @Log(title = "学生训练记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(studentTrainRecordService.deleteStudentTrainRecordByIds(ids));
    }
}
