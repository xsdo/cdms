package com.qx.student.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.qx.common.utils.DateUtils;
import com.qx.student.domain.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.student.mapper.CaseForumMapper;
import com.qx.student.domain.CaseForum;
import com.qx.student.service.ICaseForumService;

/**
 * 论坛Service业务层处理
 * 
 * @author aaa
 * @date 2021-12-20
 */
@Service
public class CaseForumServiceImpl implements ICaseForumService 
{
    @Autowired
    private CaseForumMapper caseForumMapper;

    /**
     * 查询论坛
     * 
     * @param id 论坛ID
     * @return 论坛
     */
    @Override
    public CaseForum selectCaseForumById(Long id)
    {
        return caseForumMapper.selectCaseForumById(id);
    }

    @Override
    public CaseForum selectCaseForumAndDiscussById(Long id)
    {
        CaseForum caseForum=caseForumMapper.selectCaseForumById(id);
        CaseForum discuss=new CaseForum();
        discuss.setPid(id);
        List<CaseForum>discussList=caseForumMapper.selectCaseForumList(discuss);
        if (discussList.size()>0&&discussList!=null){
            for (CaseForum discuss1:discussList){
                CaseForum reply =new CaseForum();
                reply.setPid(discuss1.getId());
                List<CaseForum>replyList=caseForumMapper.selectCaseForumList(reply);
                discuss1.setCaseForumList(replyList);
            }
            caseForum.setCaseForumList(discussList);
        }
        return caseForum;
    }

    /**
     * 查询论坛列表
     * 
     * @param caseForum 论坛
     * @return 论坛
     */
    @Override
    public List<CaseForum> selectCaseForumList(CaseForum caseForum)
    {
        return caseForumMapper.selectCaseForumList(caseForum);
    }

    @Override
    public int getReplyCount(Long forumId){
        return caseForumMapper.getReplyCount(forumId);
    }

    @Override
    public List<ReplyVo> getReply(){
        List<ReplyVo> replyVoList=new ArrayList<>();
        CaseForum caseForum =new CaseForum();
        caseForum.setPid(0L);
        List<CaseForum> list = caseForumMapper.selectCaseForumList(caseForum);
        for (CaseForum caseForum1:list){
            ReplyVo replyVo=new ReplyVo();
            replyVo.setForumId(caseForum1.getId());
            replyVo.setForumCount(caseForumMapper.getReplyCount(caseForum1.getId()));
            replyVo.setCaseForum(caseForum1);
            replyVoList.add(replyVo);
        }
        return replyVoList;
    }
    /**
     * 新增论坛
     * 
     * @param caseForum 论坛
     * @return 结果
     */
    @Override
    public int insertCaseForum(CaseForum caseForum)
    {
        caseForum.setCreateTime(DateUtils.getNowDate());
        return caseForumMapper.insertCaseForum(caseForum);
    }

    /**
     * 修改论坛
     * 
     * @param caseForum 论坛
     * @return 结果
     */
    @Override
    public int updateCaseForum(CaseForum caseForum)
    {
        caseForum.setUpdateTime(DateUtils.getNowDate());
        return caseForumMapper.updateCaseForum(caseForum);
    }

    /**
     * 批量删除论坛
     * 
     * @param ids 需要删除的论坛ID
     * @return 结果
     */
    @Override
    public int deleteCaseForumByIds(Long[] ids)
    {
        return caseForumMapper.deleteCaseForumByIds(ids);
    }

    /**
     * 删除论坛信息
     * 
     * @param id 论坛ID
     * @return 结果
     */
    @Override
    public int deleteCaseForumById(Long id)
    {
        return caseForumMapper.deleteCaseForumById(id);
    }
}
