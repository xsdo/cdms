package com.qx.cases.service;

import com.qx.cases.domain.CaseImpLearning;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * 学习项目Service接口
 * 
 * @author aaa
 * @date 2021-08-16
 */
public interface ICaseImpLearningService 
{
    /**
     * 查询学习项目
     * 
     * @param learningId 学习项目ID
     * @return 学习项目
     */
    public CaseImpLearning selectCaseImpLearningById(Long learningId);

    /**
     * 查询学习项目列表
     * 
     * @param caseImpLearning 学习项目
     * @return 学习项目集合
     */
    public List<CaseImpLearning> selectCaseImpLearningList(CaseImpLearning caseImpLearning);

    /**
     * 新增学习项目
     * 
     * @param caseImpLearning 学习项目
     * @return 结果
     */
    public int insertCaseImpLearning(CaseImpLearning caseImpLearning);

    /**
     * 修改学习项目
     * 
     * @param caseImpLearning 学习项目
     * @return 结果
     */
    public int updateCaseImpLearning(CaseImpLearning caseImpLearning);

    /**
     * 批量删除学习项目
     * 
     * @param learningIds 需要删除的学习项目ID
     * @return 结果
     */
    public int deleteCaseImpLearningByIds(Long[] learningIds);

    /**
     * 删除学习项目信息
     * 
     * @param learningId 学习项目ID
     * @return 结果
     */
    public int deleteCaseImpLearningById(Long learningId);

    List<CaseImpLearning> buildLearningTree(List<CaseImpLearning> learnings);

    List<TreeSelect> buildLearningTreeSelect(List<CaseImpLearning> learnings);

    boolean hasChildById(Long id);

    List<CaseImpLearning> selectTreatmentByIds(Long[] ids);
}
