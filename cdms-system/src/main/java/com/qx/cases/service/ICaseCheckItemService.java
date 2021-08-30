package com.qx.cases.service;

import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.domain.CaseQuestionType;
import com.qx.system.domain.TreeSelect;

import java.util.List;

/**
 * 检查项目Service接口
 * 
 * @author aaa
 * @date 2021-05-26
 */
public interface ICaseCheckItemService 
{
    /**
     * 查询检查项目
     * 
     * @param itemId 检查项目ID
     * @return 检查项目
     */
    public CaseCheckItem selectCaseCheckItemById(Long itemId);

    /**
     * 查询检查项目列表
     * 
     * @param caseCheckItem 检查项目
     * @return 检查项目集合
     */
    public List<CaseCheckItem> selectCaseCheckItemList(CaseCheckItem caseCheckItem);

    List<CaseCheckItem> selectCaseCheckItemByIds(Long[] itemIds);

    /**
     * 新增检查项目
     * 
     * @param caseCheckItem 检查项目
     * @return 结果
     */
    public int insertCaseCheckItem(CaseCheckItem caseCheckItem);

    /**
     * 修改检查项目
     * 
     * @param caseCheckItem 检查项目
     * @return 结果
     */
    public int updateCaseCheckItem(CaseCheckItem caseCheckItem);

    /**
     * 批量删除检查项目
     * 
     * @param itemIds 需要删除的检查项目ID
     * @return 结果
     */
    public int deleteCaseCheckItemByIds(Long[] itemIds);

    /**
     * 删除检查项目信息
     * 
     * @param itemId 检查项目ID
     * @return 结果
     */
    public int deleteCaseCheckItemById(Long itemId);

    /**
     * 构建前端所需要树结构
     *
     * @param list 列表
     * @return 树结构列表
     */
    List<CaseCheckItem> buildItemTree(List<CaseCheckItem> list);
    /**
     * 构建前端所需要下拉树结构
     *
     * @param list 列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildItemTreeSelect(List<CaseCheckItem> list);
    /**
     * 是否存在子项目
     * @param itemId
     * @return 结果 true 存在 false 不存在
     */
    boolean hasChildByItemId(Long itemId);
}
