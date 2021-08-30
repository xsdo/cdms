package com.qx.cases.mapper;

import com.qx.cases.domain.CaseCheckItem;
import java.util.List;

/**
 * 检查项目Mapper接口
 * 
 * @author aaa
 * @date 2021-05-26
 */
public interface CaseCheckItemMapper 
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


    public List<CaseCheckItem> selectCaseCheckItemByIds (Long[] itenIds);
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
     * 删除检查项目
     * 
     * @param itemId 检查项目ID
     * @return 结果
     */
    public int deleteCaseCheckItemById(Long itemId);

    /**
     * 批量删除检查项目
     * 
     * @param itemIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCaseCheckItemByIds(Long[] itemIds);

    /**
     * 是否存在子项目
     * @param itemId
     * @return
     */
    int hasChildByItemId(Long itemId);
}
