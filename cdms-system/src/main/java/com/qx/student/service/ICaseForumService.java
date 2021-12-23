package com.qx.student.service;

import com.qx.student.domain.CaseForum;
import com.qx.student.domain.vo.ReplyVo;

import java.util.List;

/**
 * 论坛Service接口
 * 
 * @author aaa
 * @date 2021-12-20
 */
public interface ICaseForumService 
{
    /**
     * 查询论坛
     * 
     * @param id 论坛ID
     * @return 论坛
     */
    public CaseForum selectCaseForumById(Long id);

    CaseForum selectCaseForumAndDiscussById(Long id);

    /**
     * 查询论坛列表
     * 
     * @param caseForum 论坛
     * @return 论坛集合
     */
    public List<CaseForum> selectCaseForumList(CaseForum caseForum);

    int getReplyCount(Long forumId);

    public List<ReplyVo> getReply();

    /**
     * 新增论坛
     * 
     * @param caseForum 论坛
     * @return 结果
     */
    public int insertCaseForum(CaseForum caseForum);

    /**
     * 修改论坛
     * 
     * @param caseForum 论坛
     * @return 结果
     */
    public int updateCaseForum(CaseForum caseForum);

    /**
     * 批量删除论坛
     * 
     * @param ids 需要删除的论坛ID
     * @return 结果
     */
    public int deleteCaseForumByIds(Long[] ids);

    /**
     * 删除论坛信息
     * 
     * @param id 论坛ID
     * @return 结果
     */
    public int deleteCaseForumById(Long id);
}
