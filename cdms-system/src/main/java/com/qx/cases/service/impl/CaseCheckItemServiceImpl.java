package com.qx.cases.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.cases.domain.CaseQuestionType;
import com.qx.common.utils.DateUtils;
import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseCheckItemMapper;
import com.qx.cases.domain.CaseCheckItem;
import com.qx.cases.service.ICaseCheckItemService;

/**
 * 检查项目Service业务层处理
 * 
 * @author aaa
 * @date 2021-05-26
 */
@Service
public class CaseCheckItemServiceImpl implements ICaseCheckItemService 
{
    @Autowired
    private CaseCheckItemMapper caseCheckItemMapper;

    /**
     * 查询检查项目
     * 
     * @param itemId 检查项目ID
     * @return 检查项目
     */
    @Override
    public CaseCheckItem selectCaseCheckItemById(Long itemId)
    {
        return caseCheckItemMapper.selectCaseCheckItemById(itemId);
    }

    /**
     * 查询检查项目列表
     * 
     * @param caseCheckItem 检查项目
     * @return 检查项目
     */
    @Override
    public List<CaseCheckItem> selectCaseCheckItemList(CaseCheckItem caseCheckItem)
    {
        return caseCheckItemMapper.selectCaseCheckItemList(caseCheckItem);
    }

    @Override
    public List<CaseCheckItem> selectCaseCheckItemByIds(Long[]itemIds){
        return caseCheckItemMapper.selectCaseCheckItemByIds(itemIds);
    }
    /**
     * 新增检查项目
     * 
     * @param caseCheckItem 检查项目
     * @return 结果
     */
    @Override
    public int insertCaseCheckItem(CaseCheckItem caseCheckItem)
    {
        caseCheckItem.setCreateTime(DateUtils.getNowDate());
        return caseCheckItemMapper.insertCaseCheckItem(caseCheckItem);
    }

    /**
     * 修改检查项目
     * 
     * @param caseCheckItem 检查项目
     * @return 结果
     */
    @Override
    public int updateCaseCheckItem(CaseCheckItem caseCheckItem)
    {
        caseCheckItem.setUpdateTime(DateUtils.getNowDate());
        return caseCheckItemMapper.updateCaseCheckItem(caseCheckItem);
    }

    /**
     * 批量删除检查项目
     * 
     * @param itemIds 需要删除的检查项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseCheckItemByIds(Long[] itemIds)
    {
        return caseCheckItemMapper.deleteCaseCheckItemByIds(itemIds);
    }

    /**
     * 删除检查项目信息
     * 
     * @param itemId 检查项目ID
     * @return 结果
     */
    @Override
    public int deleteCaseCheckItemById(Long itemId)
    {
        return caseCheckItemMapper.deleteCaseCheckItemById(itemId);
    }


    /**
     * 构建前端所需要树结构
     *
     * @param list 列表
     * @return 树结构列表
     */
    @Override
    public List<CaseCheckItem> buildItemTree(List<CaseCheckItem> list) {
        List<CaseCheckItem> returnList = new ArrayList<>();
        for (Iterator<CaseCheckItem> iterator = list.iterator(); iterator.hasNext();)
        {
            CaseCheckItem t = (CaseCheckItem) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == 0)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = list;
        }
        return returnList;
    }

    private void recursionFn(List<CaseCheckItem> list, CaseCheckItem t) {
        // 得到子节点列表
        List<CaseCheckItem> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CaseCheckItem tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<CaseCheckItem> it = childList.iterator();
                while (it.hasNext())
                {
                    CaseCheckItem n = (CaseCheckItem) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    private boolean hasChild(List<CaseCheckItem> list, CaseCheckItem tChild) {
        return getChildList(list, tChild).size() > 0 ? true : false;
    }

    private List<CaseCheckItem> getChildList(List<CaseCheckItem> list, CaseCheckItem t) {
        List<CaseCheckItem> tlist = new ArrayList<>();
        Iterator<CaseCheckItem> it = list.iterator();
        while (it.hasNext())
        {
            CaseCheckItem n = (CaseCheckItem) it.next();
            if (n.getPid().longValue() == t.getItemId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param list 列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildItemTreeSelect(List<CaseCheckItem> list) {
        List<CaseCheckItem> typeTrees = buildItemTree(list);
        return typeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());

    }

    @Override
    public boolean hasChildByItemId(Long itemId) {
        int result = caseCheckItemMapper.hasChildByItemId(itemId);
        return result > 0 ? true : false;
    }
}
