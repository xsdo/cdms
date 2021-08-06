package com.qx.cases.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.qx.system.domain.SysMenu;
import com.qx.system.domain.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qx.cases.mapper.CaseQuestionTypeMapper;
import com.qx.cases.domain.CaseQuestionType;
import com.qx.cases.service.ICaseQuestionTypeService;

/**
 * 问诊问答类型Service业务层处理
 * 
 * @author aaa
 * @date 2021-05-25
 */
@Service
public class CaseQuestionTypeServiceImpl implements ICaseQuestionTypeService 
{
    @Autowired
    private CaseQuestionTypeMapper caseQuestionTypeMapper;

    /**
     * 查询问诊问答类型
     * 
     * @param id 问诊问答类型ID
     * @return 问诊问答类型
     */
    @Override
    public CaseQuestionType selectCaseQuestionTypeById(Long id)
    {
        return caseQuestionTypeMapper.selectCaseQuestionTypeById(id);
    }

    /**
     * 查询问诊问答类型列表
     * 
     * @param caseQuestionType 问诊问答类型
     * @return 问诊问答类型
     */
    @Override
    public List<CaseQuestionType> selectCaseQuestionTypeList(CaseQuestionType caseQuestionType)
    {
        return caseQuestionTypeMapper.selectCaseQuestionTypeList(caseQuestionType);
    }

    /**
     * 新增问诊问答类型
     * 
     * @param caseQuestionType 问诊问答类型
     * @return 结果
     */
    @Override
    public int insertCaseQuestionType(CaseQuestionType caseQuestionType)
    {
        return caseQuestionTypeMapper.insertCaseQuestionType(caseQuestionType);
    }

    /**
     * 修改问诊问答类型
     * 
     * @param caseQuestionType 问诊问答类型
     * @return 结果
     */
    @Override
    public int updateCaseQuestionType(CaseQuestionType caseQuestionType)
    {
        return caseQuestionTypeMapper.updateCaseQuestionType(caseQuestionType);
    }

    /**
     * 批量删除问诊问答类型
     * 
     * @param ids 需要删除的问诊问答类型ID
     * @return 结果
     */
    @Override
    public int deleteCaseQuestionTypeByIds(Long[] ids)
    {
        return caseQuestionTypeMapper.deleteCaseQuestionTypeByIds(ids);
    }

    /**
     * 删除问诊问答类型信息
     * 
     * @param id 问诊问答类型ID
     * @return 结果
     */
    @Override
    public int deleteCaseQuestionTypeById(Long id)
    {
        return caseQuestionTypeMapper.deleteCaseQuestionTypeById(id);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param types 类型列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildTypeTreeSelect(List<CaseQuestionType> types) {
        List<CaseQuestionType> typeTrees = buildTypeTree(types);
        return typeTrees.stream().map(TreeSelect::new).collect(Collectors.toList());

    }
    /**
     * 构建前端所需要树结构
     *
     * @param types 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<CaseQuestionType> buildTypeTree(List<CaseQuestionType> types) {
        List<CaseQuestionType> returnList = new ArrayList<>();
        for (Iterator<CaseQuestionType> iterator = types.iterator(); iterator.hasNext();)
        {
            CaseQuestionType t = (CaseQuestionType) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getPid() == 0)
            {
                recursionFn(types, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = types;
        }
        return returnList;

    }
    /**
     * 是否存在子类型
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean hasChildById(Long id) {
        int result = caseQuestionTypeMapper.hasChildById(id);
        return result > 0 ? true : false;
    }

    /**
     * 查询类型下是否存在问答
     * @param id id
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkTypeExistQuestion(Long id) {
        int result = caseQuestionTypeMapper.checkTypeExistQuestion(id);
        return result > 0 ? true : false;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<CaseQuestionType> list, CaseQuestionType t) {
        // 得到子节点列表
        List<CaseQuestionType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CaseQuestionType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<CaseQuestionType> it = childList.iterator();
                while (it.hasNext())
                {
                    CaseQuestionType n = (CaseQuestionType) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 获取子节点
     * @param list
     * @param t
     * @return
     */
    private List<CaseQuestionType> getChildList(List<CaseQuestionType> list, CaseQuestionType t)
    {
        List<CaseQuestionType> tlist = new ArrayList<>();
        Iterator<CaseQuestionType> it = list.iterator();
        while (it.hasNext())
        {
            CaseQuestionType n = (CaseQuestionType) it.next();
            if (n.getPid().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     * @param list
     * @param t
     * @return
     */
    private boolean hasChild(List<CaseQuestionType> list, CaseQuestionType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
