package com.qx.cases.service;

import com.qx.cases.domain.CaseQuestion;
import java.util.List;

/**
 * 问诊问答Service接口
 * 
 * @author aaa
 * @date 2021-05-25
 */
public interface ICaseQuestionService 
{
    /**
     * 查询问诊问答
     * 
     * @param id 问诊问答ID
     * @return 问诊问答
     */
    public CaseQuestion selectCaseQuestionById(Long id);

    /**
     * 查询问诊问答列表
     * 
     * @param caseQuestion 问诊问答
     * @return 问诊问答集合
     */
    public List<CaseQuestion> selectCaseQuestionList(CaseQuestion caseQuestion);

    /**
     * 新增问诊问答
     * 
     * @param caseQuestion 问诊问答
     * @return 结果
     */
    public int insertCaseQuestion(CaseQuestion caseQuestion);

    /**
     * 修改问诊问答
     * 
     * @param caseQuestion 问诊问答
     * @return 结果
     */
    public int updateCaseQuestion(CaseQuestion caseQuestion);

    /**
     * 批量删除问诊问答
     * 
     * @param ids 需要删除的问诊问答ID
     * @return 结果
     */
    public int deleteCaseQuestionByIds(Long[] ids);

    /**
     * 删除问诊问答信息
     * 
     * @param id 问诊问答ID
     * @return 结果
     */
    public int deleteCaseQuestionById(Long id);

    /**
     * 查询问诊问答列表，及其所属类型，类型父类型
     * @param list
     * @return
     */
    List<CaseQuestion> selectCaseQuestionListType(List<CaseQuestion> list);

    List<CaseQuestion> selectCaseQuestionByIds(Long[] ids);

}
