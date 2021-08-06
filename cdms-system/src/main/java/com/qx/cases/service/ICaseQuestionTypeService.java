package com.qx.cases.service;

import com.qx.cases.domain.CaseQuestionType;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * 问诊问答类型Service接口
 * 
 * @author aaa
 * @date 2021-05-25
 */
public interface ICaseQuestionTypeService 
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
     * 批量删除问诊问答类型
     * 
     * @param ids 需要删除的问诊问答类型ID
     * @return 结果
     */
    public int deleteCaseQuestionTypeByIds(Long[] ids);

    /**
     * 删除问诊问答类型信息
     * 
     * @param id 问诊问答类型ID
     * @return 结果
     */
    public int deleteCaseQuestionTypeById(Long id);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param types 类型列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildTypeTreeSelect(List<CaseQuestionType> types);

    /**
     * 构建前端所需要树结构
     *
     * @param types 类型列表
     * @return 树结构列表
     */
    List<CaseQuestionType> buildTypeTree(List<CaseQuestionType> types);

    /**
     * 是否存在子类型
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildById(Long id);

    /**
     * 查询类型下是否存在问答
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    boolean checkTypeExistQuestion(Long id);
}
