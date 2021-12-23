package com.qx.student.mapper;

import com.qx.student.domain.CaseForum;
import java.util.List;

/**
 * 论坛Mapper接口
 * 
 * @author aaa
 * @date 2021-12-20
 */
public interface CaseForumMapper 
{
    /**
     * 查询论坛
     * 
     * @param id 论坛ID
     * @return 论坛
     */
    public CaseForum selectCaseForumById(Long id);

    /**
     * 查询论坛列表
     * 
     * @param caseForum 论坛
     * @return 论坛集合
     */
    public List<CaseForum> selectCaseForumList(CaseForum caseForum);



    public int getReplyCount(Long forumId);

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
     * 删除论坛
     * 
     * @param id 论坛ID
     * @return 结果
     */
    public int deleteCaseForumById(Long id);

    /**
     * 批量删除论坛
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseForumByIds(Long[] ids);
}
