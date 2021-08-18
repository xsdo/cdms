package com.qx.cases.mapper;

import com.qx.cases.domain.CaseImpLearning;
import com.qx.cases.domain.CaseTreatment;

import java.util.List;

/**
 * 学习项目Mapper接口
 * 
 * @author aaa
 * @date 2021-08-16
 */
public interface CaseImpLearningMapper 
{
    /**
     * 查询学习项目
     * 
     * @param learningId 学习项目ID
     * @return 学习项目
     */
    public CaseImpLearning selectCaseImpLearningById(Long learningId);


    public List<CaseImpLearning> selectCaseTreatmentByIds(Long[] learningId);
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
     * 删除学习项目
     * 
     * @param learningId 学习项目ID
     * @return 结果
     */
    public int deleteCaseImpLearningById(Long learningId);

    /**
     * 批量删除学习项目
     * 
     * @param learningIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseImpLearningByIds(Long[] learningIds);

    int hasChildById(Long learningId);
}
