package com.qx.cases.mapper;

import com.qx.cases.domain.CaseQuestionType;
import java.util.List;

/**
 * 问诊问答类型Mapper接口
 * 
 * @author aaa
 * @date 2021-05-25
 */
public interface CaseQuestionTypeMapper 
{
    /**
     * 查询问诊问答类型
     * 
     * @param id 问诊问答类型ID
     * @return 问诊问答类型
     */
    public CaseQuestionType selectCaseQuestionTypeById(Long id);

    /**
     * 查询问诊问答类型列表
     * 
     * @param caseQuestionType 问诊问答类型
     * @return 问诊问答类型集合
     */
    public List<CaseQuestionType> selectCaseQuestionTypeList(CaseQuestionType caseQuestionType);

    /**
     * 新增问诊问答类型
     * 
     * @param caseQuestionType 问诊问答类型
     * @return 结果
     */
    public int insertCaseQuestionType(CaseQuestionType caseQuestionType);

    /**
     * 修改问诊问答类型
     * 
     * @param caseQuestionType 问诊问答类型
     * @return 结果
     */
    public int updateCaseQuestionType(CaseQuestionType caseQuestionType);

    /**
     * 删除问诊问答类型
     * 
     * @param id 问诊问答类型ID
     * @return 结果
     */
    public int deleteCaseQuestionTypeById(Long id);

    /**
     * 批量删除问诊问答类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseQuestionTypeByIds(Long[] ids);

    /**
     * 是否存在子类型
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    int hasChildById(Long id);

    /**
     * 查询类型下是否存在问答
     * @param id id
     * @return 结果 true 存在 false 不存在
     */

    int checkTypeExistQuestion(Long id);
}
