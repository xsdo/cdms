package com.qx.web.controller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qx.student.domain.vo.ReplyVo;
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
import com.qx.student.domain.CaseForum;
import com.qx.student.service.ICaseForumService;

import com.qx.common.annotation.Log;
import com.qx.common.core.controller.BaseController;
import com.qx.common.core.domain.AjaxResult;
import com.qx.common.core.page.TableDataInfo;
import com.qx.common.enums.BusinessType;
import com.qx.common.utils.poi.ExcelUtil;



/**
 * 论坛Controller
 * 
 * @author aaa
 * @date 2021-12-20
 */
@RestController
@RequestMapping("/student/forum")
public class CaseForumController extends BaseController
{
    @Autowired
    private ICaseForumService caseForumService;

    /**
     * 查询论坛列表
     */
//    @PreAuthorize("@ss.hasPermi('student:forum:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseForum caseForum)
    {
        startPage();
        caseForum.setPid(0L);
        List<CaseForum> list = caseForumService.selectCaseForumList(caseForum);
        return getDataTable(list);
    }
    //获取帖子回复
    @GetMapping("/getReply")
    public AjaxResult getReply(){
        AjaxResult ajaxResult=AjaxResult.success();
        List<ReplyVo> replyVoList =caseForumService.getReply();
        ajaxResult.put("reply",replyVoList);
        return ajaxResult;
    }

    //发表主题
    @PostMapping("/addCaseForum")
    public AjaxResult addCaseForum(@RequestBody CaseForum caseForum)
    {
        caseForum.setCreateTime(new Date());
        caseForum.setUpdateTime(new Date());
        if (caseForum.getRemark()!=null){
            if (caseForum.getRemark().equals("1")){
                caseForum.setStudentName("匿名发表");
            }
        }
        if (caseForum.getPid()!=0){
            CaseForum caseForumTheme=caseForumService.selectCaseForumById(caseForum.getPid());
            caseForumTheme.setUpdateTime(new Date());
            caseForumService.updateCaseForum(caseForumTheme);
        }

        return toAjax(caseForumService.insertCaseForum(caseForum));
    }

    @GetMapping("/getDiscuss")
    public AjaxResult getDiscuss(Long id){
        return AjaxResult.success(caseForumService.selectCaseForumAndDiscussById(id));
    }
    /**
     * 获取论坛详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:forum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(caseForumService.selectCaseForumById(id));
    }

    /**
     * 新增论坛
     */
    @PreAuthorize("@ss.hasPermi('student:forum:add')")
    @Log(title = "论坛", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseForum caseForum)
    {
        return toAjax(caseForumService.insertCaseForum(caseForum));
    }

    /**
     * 修改论坛
     */
    @PreAuthorize("@ss.hasPermi('student:forum:edit')")
    @Log(title = "论坛", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseForum caseForum)
    {
        return toAjax(caseForumService.updateCaseForum(caseForum));
    }

    /**
     * 删除论坛
     */
    @PreAuthorize("@ss.hasPermi('student:forum:remove')")
    @Log(title = "论坛", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(caseForumService.deleteCaseForumByIds(ids));
    }
}
