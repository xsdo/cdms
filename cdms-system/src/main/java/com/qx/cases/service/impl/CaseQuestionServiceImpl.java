package com.qx.cases.service.impl;

import java.util.List;

import com.qx.cases.domain.CaseQuestionType;
import com.qx.cases.mapper.CaseQuestionTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseQuestionMapper;
import com.qx.cases.domain.CaseQuestion;
import com.qx.cases.service.ICaseQuestionService;

/**
 * 问诊问答Service业务层处理
 * 
 * @author aaa
 * @date 2021-05-25
 */
@Service
public class CaseQuestionServiceImpl implements ICaseQuestionService 
{
    @Autowired
    private CaseQuestionMapper caseQuestionMapper;

    @Autowired
    private CaseQuestionTypeMapper caseQuestionTypeMapper;
    /**
     * 查询问诊问答
     * 
     * @param id 问诊问答ID
     * @return 问诊问答
     */
    @Override
    public CaseQuestion selectCaseQuestionById(Long id)
    {
        return caseQuestionMapper.selectCaseQuestionById(id);
    }

    /**
     * 查询问诊问答列表
     * 
     * @param caseQuestion 问诊问答
     * @return 问诊问答
     */
    @Override
    public List<CaseQuestion> selectCaseQuestionList(CaseQuestion caseQuestion)
    {
        return caseQuestionMapper.selectCaseQuestionList(caseQuestion);
    }

    /**
     * 新增问诊问答
     * 
     * @param caseQuestion 问诊问答
     * @return 结果
     */
    @Override
    public int insertCaseQuestion(CaseQuestion caseQuestion)
    {
        return caseQuestionMapper.insertCaseQuestion(caseQuestion);
    }

    /**
     * 修改问诊问答
     * 
     * @param caseQuestion 问诊问答
     * @return 结果
     */
    @Override
    public int updateCaseQuestion(CaseQuestion caseQuestion)
    {
        return caseQuestionMapper.updateCaseQuestion(caseQuestion);
    }

    /**
     * 批量删除问诊问答
     * 
     * @param ids 需要删除的问诊问答ID
     * @return 结果
     */
    @Override
    public int deleteCaseQuestionByIds(Long[] ids)
    {
        return caseQuestionMapper.deleteCaseQuestionByIds(ids);
    }

    /**
     * 删除问诊问答信息
     * 
     * @param id 问诊问答ID
     * @return 结果
     */
    @Override
    public int deleteCaseQuestionById(Long id)
    {
        return caseQuestionMapper.deleteCaseQuestionById(id);
    }

    /**
     * 查询问诊问答列表，及其所属类型，类型父类型
     * @param list
     * @return
     */
    @Override
    public List<CaseQuestion> selectCaseQuestionListType(List<CaseQuestion> list) {

        for(CaseQuestion question : list){
            CaseQuestionType type = question.getType();
            recursionFn(type);
        }
        return list;
    }

    /**
     * 批量查询问题列表
     * @param ids
     * @return
     */
    @Override
    public List<CaseQuestion> selectCaseQuestionByIds(Long[] ids) {
        if (ids!=null&&ids.length>0){
            return caseQuestionMapper.selectCaseQuestionByIds(ids);
        }else {
            return null;
        }
    }

    private void recursionFn(CaseQuestionType type) {

        if (0 != type.getPid()) {
            //获取该类型的父级类型
            CaseQuestionType fType = caseQuestionTypeMapper.selectCaseQuestionTypeById(type.getPid());
            type.setpType(fType);
            recursionFn(fType);
        }
    }
}
